package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;

@SpringBootApplication
//1.jetcache启用缓存的主开关
@EnableCreateCacheAnnotation
//打开方法缓存注解,basePackages中配置你要覆盖到使用注解方法的包
@EnableMethodCache(basePackages = "com.example")
public class Springboot20JetcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot20JetcacheApplication.class, args);
    }

}
