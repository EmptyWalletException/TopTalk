package com.toptalk.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Description:
 * @Author: MasterCV
 * @Date: Created in  2018/9/30 17:25
 */
@EnableZuulProxy
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
