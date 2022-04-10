package com.example.rabbitmq.direct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDirectConfig {
    @Bean
    public Queue directQueue() {
        // durable:是否持久化,默认true
        // exclusive:是否当前连接专用， 默认false，连接关闭后队列即被删除
        // autoDelete:是否自动删除，默认false， 当生产者或消费者不再使用此队列，自动删除
        return new Queue("direct_queue", true, false, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding bindingDirect() {
        //直连模式没有匹配规则*或#
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct");
    }

    /**
     * 相当于把direct_queue和direct_queue2绑定到同一个directExchange
     * @return
     */
    @Bean
    public Queue directQueue2() {
        return new Queue("direct_queue2");
    }

    @Bean
    public Binding bindingDirect2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("direct2");
    }
}
