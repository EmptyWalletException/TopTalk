package com.toptalk.manager.component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: MasterCV
 * @Date: Created in  2018/9/30 17:57
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体的拦截业务;
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //获取request
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //获取请求路径;
        String url = request.getRequestURL().toString();
        //判断不拦截登陆url;
        if (url.indexOf("/admin/login") >0){
            System.out.println("登陆页面"+url);
            return null;
        }
        //获取请求头;
        String authHeader = (String)request.getHeader("Authorization");
        if (null != authHeader && authHeader.startsWith("Bearer")){
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (null != null){
                if ("admin".equals(claims.get("roles"))){
                    requestContext.addZuulRequestHeader("Authorization",authHeader);
                    System.out.println("token验证通过,添加头信息"+authHeader);
                    return null;
                }
            }
        }

        //当上面if没有执行,说明没有请求中没带权限,不能放行;
        requestContext.setSendZuulResponse(false);//终止运行;
        requestContext.setResponseStatusCode(401);
        requestContext.setResponseBody("访问权限不足!");
        requestContext.getResponse().setContentType("text/html;charset=UTF-8");
        return null;
    }
}
