package com.example.rabbitmq.direct.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//rabbitmq消息队列监听器
@Component
public class RabbitDirectMessageListener2 {

    @RabbitListener(queues = "direct_queue")
    public void receive(String id) {
        System.out.println("已完成短信发送业务（rabbitmq direct listener2）:id" + id);
    }
}
