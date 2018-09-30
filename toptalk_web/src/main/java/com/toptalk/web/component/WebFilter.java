package com.toptalk.web.component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:    继承了zuul过滤器的web过滤器;
 * @Author: MasterCV
 * @Date: Created in  2018/9/30 17:34
 */
@Component
public class WebFilter extends ZuulFilter {

    /**
     * pre : 可以在请求被路由之前调用
     * route : 在路由请求时被调用;
     * post : 在route和error过滤器之后被调用;
     * error : 处理请求时发生错误时被调用;
     * @return
     */
    @Override
    public String filterType() {
        return "pre";//前置过滤器;

    }

    /**
     * 通过int值来定义过滤器的执行顺序,优先级为0,数字越大,优先级越低;
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器,此处为true,说明需要过滤;
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;//
    }

    /**
     * 过滤器的具体执行逻辑;
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //获取requestComtext;
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取header;
        HttpServletRequest request = requestContext.getRequest();
        String authorization = request.getHeader("Authorization");
        //向header中添加鉴权令牌
        if (null != authorization){
            requestContext.addZuulRequestHeader("Authorization",authorization);
        }
        return null;
    }
}
