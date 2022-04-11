package com.example.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.service.OrderKafkaService;
import com.example.rocketmq.service.OrderRocketMQService;

@RestController
@RequestMapping(value = "/orderKafka")
public class OrderKafkaController {
    @Autowired
    private OrderKafkaService orderKafkaService;

    @PostMapping("/{id}")
    public void order(@PathVariable(value = "id") String id) {
        orderKafkaService.order(id);
    }
}
