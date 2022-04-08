package com.example.activemq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activemq.service.MessageActiveMQService;
import com.example.activemq.service.OrderActiveMQService;

@Service
public class OrderActiveMQServiceImpl implements OrderActiveMQService {

    @Autowired
    private MessageActiveMQService messageActiveMQService;

    @Override
    public void order(String id) {
        //一系列操作，包含各种服务调用，处理各种业务
        System.out.println("业务订单开始处理");
        //短信消息处理
        messageActiveMQService.sendMessage(id);
        System.out.println("业务订单处理结束");
        System.out.println("================================");
    }
}
