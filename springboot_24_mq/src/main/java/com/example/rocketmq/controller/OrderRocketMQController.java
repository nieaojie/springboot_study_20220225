package com.example.rocketmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rocketmq.service.OrderRocketMQService;

@RestController
@RequestMapping(value = "/orderRocketMQ")
public class OrderRocketMQController {
    @Autowired
    private OrderRocketMQService orderRocketMQService;

    @PostMapping("/{id}")
    public void order(@PathVariable(value = "id") String id) {
        orderRocketMQService.order(id);
    }
}
