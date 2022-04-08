package com.example.rabbitmq.topic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rabbitmq.topic.service.MessageTopicService;
import com.example.rabbitmq.topic.service.OrderTopicService;

@Service
public class OrderTopicServiceImpl implements OrderTopicService {

    @Autowired
    private MessageTopicService messageTopicService;

    @Override
    public void order(String id) {
        System.out.println("================================");
        //一系列操作，包含各种服务调用，处理各种业务
        System.out.println("业务订单开始处理");
        //短信消息处理
        messageTopicService.sendMessage(id);
        System.out.println("业务订单处理结束");
        System.out.println("================================");
    }
}
