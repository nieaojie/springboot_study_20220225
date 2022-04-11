package com.example.kafka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kafka.service.MessageKafkaService;
import com.example.kafka.service.OrderKafkaService;

/**
 * @author: nie
 * @create: 2022-04-11 18:03
 * @description:
 **/

@Service
public class OrderKafkaServiceImpl implements OrderKafkaService {

    @Autowired
    private MessageKafkaService messageKafkaService;

    @Override
    public void order(String id) {
        System.out.println("================================");
        //一系列操作，包含各种服务调用，处理各种业务
        System.out.println("业务订单开始处理");
        //短信消息处理
        messageKafkaService.sendMessage(id);
        System.out.println("业务订单处理结束");
        System.out.println("================================");
    }
}
