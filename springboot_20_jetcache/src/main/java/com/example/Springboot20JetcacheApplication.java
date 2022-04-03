package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;

@SpringBootApplication
//1.jetcache启用缓存的主开关
@EnableCreateCacheAnnotation
public class Springboot20JetcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot20JetcacheApplication.class, args);
    }

}
