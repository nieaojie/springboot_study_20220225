package com.example;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
class Springboot16RedisApplicationTests {

//    这里最好使用StringRedisTemplate,要不然去客户端里面会使用自己的序列化方式，RedisTemplate是以对象方式存储的。

    @Autowired
    private StringRedisTemplate redisTemplate;

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Test
    void set() {
        redisTemplate.opsForValue().set("age", "41");
    }

    @Test
    void get() {
        Object age = redisTemplate.opsForValue().get("age");
        System.out.println(age);
    }

    @Test
    void hset() {
        HashOperations ops = redisTemplate.opsForHash();
        ops.put("name", "name1", "zhangsan");
        ops.put("name", "name2", "lisi");
    }

    @Test
    void hget() {
        HashOperations ops = redisTemplate.opsForHash();
        System.out.println("name1:" + ops.get("name", "name1"));
        System.out.println("name2:" + ops.get("name", "name2"));
    }

}
