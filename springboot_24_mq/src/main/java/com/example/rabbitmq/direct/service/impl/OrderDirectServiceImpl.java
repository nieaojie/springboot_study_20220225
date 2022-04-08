package com.example.rabbitmq.direct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rabbitmq.direct.service.MessageDirectService;
import com.example.rabbitmq.direct.service.OrderDirectService;

@Service
public class OrderDirectServiceImpl implements OrderDirectService {

    @Autowired
    private MessageDirectService messageDirectService;

    @Override
    public void order(String id) {
        System.out.println("================================");
        //一系列操作，包含各种服务调用，处理各种业务
        System.out.println("业务订单开始处理");
        //短信消息处理
        messageDirectService.sendMessage(id);
        System.out.println("业务订单处理结束");
        System.out.println("================================");
    }
}
