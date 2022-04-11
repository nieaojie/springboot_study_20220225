package com.example.kafka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kafka.service.MessageKafkaService;

/**
 * @author: nie
 * @create: 2022-04-11 18:03
 * @description:
 **/

@Service
public class MessageKafkaServiceImpl implements MessageKafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * kafkaTemplate.send(String topic, @Nullable V data);
     */
    @Override
    public void sendMessage(String id) {
        kafkaTemplate.send("kafka2022", id);
        System.out.println("待发送短信的订单已纳入消息队列(kafka impl)，id:" + id);
    }
}
