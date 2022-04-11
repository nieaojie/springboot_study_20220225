package com.example.rocketmq.service.impl;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rocketmq.service.MessageRocketMQService;

/**
 * @author: nie
 * @create: 2022-04-11 16:17
 * @description:
 **/
@Service
public class MessageServiceRocketMQImpl implements MessageRocketMQService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendMessage(String id) {
        //发送的是同步消息（不太常用）
        //rocketMQTemplate.convertAndSend("order_id", id);
        //发送异步消息（常用）
        //回调函数
        SendCallback callback = new SendCallback() {
            //成功的回调函数
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("短信发送成功！！！");
            }

            //失败的回调函数
            @Override
            public void onException(Throwable throwable) {
                System.out.println("短信发送失败！！！");
            }
        };
        rocketMQTemplate.asyncSend("order_id", id, callback);
        System.out.println("待发送短信的订单已纳入消息队列(rocketmq impl)，id:" + id);
    }
}
