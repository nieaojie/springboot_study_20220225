package com.example.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.activemq.service.MessageActiveMQService;

@RestController
@RequestMapping(value = "/msgMQ")
public class MessageActiveMQController {

    @Autowired
    private MessageActiveMQService messageActiveMQService;

    @GetMapping
    public String doMessage() {
        return messageActiveMQService.doMessage();
    }
}
