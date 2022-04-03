package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: nie
 * @create: 2022-02-26 14:05
 * @description:
 **/
//日志注解，需要导入lombok依赖
@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

//    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @GetMapping
    public String getById() {
        System.out.println("springboot is running");
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");
        return "springboot is running";
    }
}
