package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dao.BookDao;

@SpringBootTest
class Springboot06MybatisPlusApplicationTests {
    @Autowired
    private BookDao bookDao;

    @Test
    void contextLoads() {
        System.out.println(bookDao.selectById(1));
    }

    @Test
    public void getAll() {
        System.out.println(bookDao.selectList(null));;
    }

}
