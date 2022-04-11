package com.example.rocketmq.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author: nie
 * @create: 2022-04-11 16:39
 * @description: 1)rocketmq监听器有自己的规范，需要实现RocketMQListener<T>接口
 *               该接口传入的泛型参数为消息队列中的消息的类型。
 *               2)@RocketMQMessageListener必须传入两个参数，topic主题，consumerGroup（配置文件中配置的）
 **/
@Component
@RocketMQMessageListener(topic = "order_id", consumerGroup = "group_rocketmq")
public class MyRocketMQMessageListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String id) {
        System.out.println("已完成短信发送业务（rocketmq listener）:id" + id);
    }
}
