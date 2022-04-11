package com.example.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: nie
 * @create: 2022-04-11 18:22
 * @description:
 **/

@Component
public class KafkaMessageListener {

    /**
     * 1)不能自定义参数类型了，需要使用ConsumerRecord。
     * 2)因为使用到了监听器，所以在注解中，需要指定一个groupId，同时也需要在配置文件中指定，否则启动会报错。
     * @param record 使用这个参数接收值
     */
    @KafkaListener(topics = "kafka2022",groupId = "group_order")
    public void onMessage(ConsumerRecord<String, String> record) {
        System.out.println("已完成短信发送业务（kafka listener）:id" + record.value());
    }
}
