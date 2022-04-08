package com.example.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.activemq.service.OrderActiveMQService;

@RestController
@RequestMapping("/orderMQ")
public class OrderActiveMQController {

    @Autowired
    private OrderActiveMQService orderActiveMQService;

    @PostMapping("/{id}")
    public void order(@PathVariable(value = "id") String id) {
        orderActiveMQService.order(id);
    }
}
