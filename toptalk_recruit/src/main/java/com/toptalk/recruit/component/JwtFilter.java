package com.toptalk.recruit.component;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:    jwt拦截器,用于判断权限,记录日志等;
 * @Author: MasterCV
 * @Date: Created in  2018/9/28 18:17
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 重写预处理方法,根据token里角色信息向request里添加角色权限信息;
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO: 2018/9/28 测试之后删除此sout控制台打印方法;
        System.out.println("进入拦截器预处理方法;");
        final String authHeader = request.getHeader("Authorization");
        if (null != authHeader && authHeader.startsWith("Bearer ")){
            final String token = authHeader.substring(7);//获取token;
            Claims claims = jwtUtil.parseJWT(token);
            //根据token里的信息向request里添加角色信息;
            if (null != claims){
                if("admin".equals(claims.get("roles"))){
                    request.setAttribute("admin_claims",claims);
                }
                if("user".equals(claims.get("roles"))){
                    request.setAttribute("user_claims",claims);
                }
            }
        }
        return true;
    }
}
