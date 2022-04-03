package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 当前仅作为测试的bean.
 */
@Configuration
public class MsgConfig {
    @Bean
    public String msg() {
        return "msg bean";
    }
}
