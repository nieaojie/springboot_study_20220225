package com.example.rabbitmq.direct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.direct.service.OrderDirectService;

@RestController
@RequestMapping(value = "/orderDirect")
public class OrderDirectController {
    @Autowired
    private OrderDirectService orderDirectService;

    @PostMapping("/{id}")
    public void order(@PathVariable(value = "id") String id) {
        orderDirectService.order(id);
    }
}
