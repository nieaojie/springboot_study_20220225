package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.DataSource;

/**
 * @author: nie
 * @create: 2022-02-26 14:05
 * @description:
 **/
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private DataSource dataSource;
    @Value("${province}")
    private String singleValue;
    //也可以封装到已经提供的Environment中
    @Autowired
    private Environment environment;

    @GetMapping
    public String getById() {
        System.out.println("springboot is running...");
        return "springboot is running...";
    }

    //获得封装属性
    @GetMapping("/dataSource")
    public DataSource getDataSource() {
        return dataSource;
    }

    //获得单一属性
    @GetMapping("/getSingleValue")
    public String getSingleValue() {
        return singleValue;
    }

    //获得配置文件中的配置
    @GetMapping("/getEnvironment")
    public String getEnvironment() {
        return environment.getProperty("country");
    }
}
