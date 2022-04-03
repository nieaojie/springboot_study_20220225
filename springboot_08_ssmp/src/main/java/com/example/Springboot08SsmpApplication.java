package com.example;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot08SsmpApplication {

    public static void main(String[] args) {

        //临时参数
        Arrays.asList(args).stream().forEach(System.out::println);

        SpringApplication.run(Springboot08SsmpApplication.class, args);
    }

}
