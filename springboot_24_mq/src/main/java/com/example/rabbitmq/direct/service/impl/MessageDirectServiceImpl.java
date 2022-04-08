package com.example.rabbitmq.direct.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rabbitmq.direct.service.MessageDirectService;

@Service
public class MessageDirectServiceImpl implements MessageDirectService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String id) {
        amqpTemplate.convertAndSend("directExchange", "direct", id);
        System.out.println("待发送短信的订单已纳入消息队列(rabbitmq direct impl)，id:" + id);
    }
}
