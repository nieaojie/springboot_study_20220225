package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mapper.BookMapper;

@SpringBootTest
public class TestSql {
    @Autowired
    private BookMapper bookMapper;

    @Test
    void testConn() {
        bookMapper.selectList(null).forEach(System.out::println);
    }
}
