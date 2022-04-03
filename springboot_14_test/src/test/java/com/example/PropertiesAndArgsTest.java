package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.testcase.domain.BookCase;
//第一种配置方法：
//1.为当前测试添加临时属性，目的是不影响外部配置
//2.当也包含外部配置时，该临时配置生效
//@SpringBootTest(properties = {"test.prop=testValue1"})

//第二种配置方法：
//args属性可以为当前测试提供临时的命令行参数
//@SpringBootTest(args = {"--test.prop=testValue2"})

//当以上两种配置都出现的时候：命令行优先级更高
@SpringBootTest(properties = { "test.prop=testValue1" }, args = { "--test.prop=testValue2" })
public class PropertiesAndArgsTest {

    @Value(value = "${test.prop}")
    private String msg;

    @Autowired
    private BookCase bookCase;

    @Test
    void testProperties() {
        System.out.println(msg);
        System.out.println(bookCase);
    }
}
