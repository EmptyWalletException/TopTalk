package com.toptalk.qa.client.impl;

import com.toptalk.qa.client.LabelClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @Description:    labelClient接口的实现类
 * @Author: MasterCV
 * @Date: Created in  2018/9/30 16:48
 */
@Component
public class LabelClientImpl implements LabelClient {
    @Override
    public Result findById(String id) {
        return new Result(false,StatusCode.ERROR,"测试熔断器启动");
    }
}
