package com.toptalk.sms.component;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:    短信消息队列监听组件;
 * @Author: MasterCV
 * @Date: Created in  2018/9/27 21:15
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    /**
     * 发送短信;    当rabbitMQ包含"sms"的消息队列有新的活动时
     * ,此组件会监听到并且获取消息体,消息体的类型是一个自定义的map,里面包含自定义的键值对;
     * @param message
     */
    @RabbitHandler
    public void sendSms(Map<String,String> message){
        // TODO: 2018/9/27 处理发送短信的业务,这里用打印消息代替;
        System.out.println("手机号: " +message.get("mobile"));
        System.out.println("验证码: " +message.get("code"));
    }
}
