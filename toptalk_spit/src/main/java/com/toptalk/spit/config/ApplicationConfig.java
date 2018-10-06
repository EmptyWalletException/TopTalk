package com.toptalk.spit.config;

import com.toptalk.spit.component.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description:    拦截器类的配置;
 * @Author: MasterCV
 * @Date: Created in  2018/9/28 18:21
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器,拦截除了登录请求之外的所有路径,目的是为了调用jwt里pre方法查看request中是否携带了权限信息;
        registry.addInterceptor(jwtFilter).addPathPatterns("/**").excludePathPatterns("/**/login");
    }
}
