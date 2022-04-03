package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mapper.BookMapper;

@SpringBootTest
class TestEnv {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void test() {
        System.out.println(bookMapper.selectById(1));
    }
}
