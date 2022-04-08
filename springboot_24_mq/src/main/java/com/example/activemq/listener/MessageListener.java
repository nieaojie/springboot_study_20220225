package com.example.activemq.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 消息队列的监听器
 */
@Component
public class MessageListener {

    //添加一个队列的监听器，只要一有消息就会被该方法接收并处理
    @JmsListener(destination = "order.queue.id")
    //该方法处理完之后，将返回值又发送到另一个消息队列处理
    @SendTo(value = "order.other.queue.id")
    public String sendMsg(String id) {
        System.out.println("已完成短信发送操作,id:" + id);
        return id;
    }

    //可以处理上一个消息队列处理之后加入新队列的消息
    @JmsListener(destination = "order.other.queue.id")
    public void processOtherMessage(String id) {
        System.out.println("完成处理订单的操作，id:" + id);
    }
}
