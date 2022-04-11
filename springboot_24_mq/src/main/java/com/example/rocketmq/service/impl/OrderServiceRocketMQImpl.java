package com.example.rocketmq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rocketmq.service.MessageRocketMQService;
import com.example.rocketmq.service.OrderRocketMQService;

/**
 * @author: nie
 * @create: 2022-04-11 16:16
 * @description:
 **/
@Service
public class OrderServiceRocketMQImpl implements OrderRocketMQService {

    @Autowired
    private MessageRocketMQService messageRocketMQService;

    @Override
    public void order(String id) {
        System.out.println("================================");
        //一系列操作，包含各种服务调用，处理各种业务
        System.out.println("业务订单开始处理");
        //短信消息处理
        messageRocketMQService.sendMessage(id);
        System.out.println("业务订单处理结束");
        System.out.println("================================");
    }
}
