package com.example.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    private ArrayList<String> list = new ArrayList<String>();

    @Override
    public void sendMessage(String id) {
        System.out.println("待发送短信的订单已纳入消息队列，id:" + id);
        list.add(id);
    }

    @Override
    public String doMessage() {
        if (list.size() <= 0) {
            return "无消息可处理";
        }
        String remove = list.remove(0);
        System.out.println("已完成短信发送业务，id:" + remove);
        return remove;
    }
}
