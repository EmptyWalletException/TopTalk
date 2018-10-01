package com.toptalk.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: MasterCV
 * @Date: Created in  2018/10/1 10:26
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 主要用于首页跳转
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index/index");
        registry.addViewController("/index").setViewName("index/index");
        registry.addViewController("/index.html").setViewName("index/index");
    }

//    /**
//     * 处理静态资源
//     * @param registry
//     */
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new HandlerInterceptor() {
//        }).excludePathPatterns("/","/index","login","/index.html","/login.html","/user/login","**/**/*.css","**/**/*.js","**/**/*.jpg","**/**/*.jpg");
//    }

}
