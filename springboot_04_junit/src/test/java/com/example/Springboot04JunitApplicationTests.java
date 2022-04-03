package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dao.BookDao;

@SpringBootTest
class Springboot04JunitApplicationTests {
    //注入要测试的对象
    @Autowired
    private BookDao bookDao;

    @Test
    void contextLoads() {
        //调用对象方法
        bookDao.save();
    }

}
