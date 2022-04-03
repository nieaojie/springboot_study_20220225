package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.example.config.MsgConfig;

@SpringBootTest
//当前不仅加载了源码级别的配置，还导入了测试用的配置。
@Import(value = { MsgConfig.class })
public class ConfigurationTest {

    @Autowired
    private String msg;

    @Test
    void testMsg() {
        System.out.println(msg);
    }
}
