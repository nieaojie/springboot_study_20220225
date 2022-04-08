package com.example.manual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.manual.service.MessageService;
import com.example.manual.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MessageService messageService;

    @Override
    public void order(String id) {
        //一系列操作，包含各种服务调用，处理各种业务
        System.out.println("业务订单开始处理");
        //短信消息处理
        messageService.sendMessage(id);
        System.out.println("业务订单处理结束");
        System.out.println("================================");
    }
}
