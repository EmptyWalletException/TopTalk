package com.toptalk.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:    调用base微服务模块的接口
 * @Author: MasterCV
 * @Date: Created in  2018/9/28 22:10
 */
@FeignClient("toptalk-base")//此注解用于指定从那个服务中调用功能
public interface LabelClient {

    @RequestMapping(value = "/label/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id);//此处因为是从微服务出调用地址,所以@PathVariable注解不能向平常一样省略参数名称;
}
