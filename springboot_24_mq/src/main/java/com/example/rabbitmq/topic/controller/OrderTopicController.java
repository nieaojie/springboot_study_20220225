package com.example.rabbitmq.topic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.topic.service.OrderTopicService;

@RestController
@RequestMapping(value = "/orderTopic")
public class OrderTopicController {
    @Autowired
    private OrderTopicService orderTopicService;

    @PostMapping("/{id}")
    public void order(@PathVariable(value = "id") String id) {
        orderTopicService.order(id);
    }
}
