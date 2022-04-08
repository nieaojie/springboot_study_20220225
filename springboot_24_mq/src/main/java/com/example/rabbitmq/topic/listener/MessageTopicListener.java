package com.example.rabbitmq.topic.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//rabbitmq消息队列监听器
@Component
public class MessageTopicListener {

    @RabbitListener(queues = "topic_queue")
    public void receive(String id) {
        System.out.println("已完成短信发送业务（rabbitmq topic listener 1）:id" + id);
    }

    @RabbitListener(queues = "topic_queue2")
    public void receive2(String id) {
        System.out.println("已完成短信发送业务（rabbitmq topic listener 2）:id" + id);
    }
}
