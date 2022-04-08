package com.example.rabbitmq.topic.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rabbitmq.topic.service.MessageTopicService;

@Service
public class MessageTopicServiceImpl implements MessageTopicService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String id) {
        //topic.order.id如果符合多个bindingTopic绑定规则的队列，那么就会进入多个队列。
        amqpTemplate.convertAndSend("topicExchange", "topic.order.id", id);
        System.out.println("待发送短信的订单已纳入消息队列(rabbitmq topic impl)，id:" + id);
    }
}
