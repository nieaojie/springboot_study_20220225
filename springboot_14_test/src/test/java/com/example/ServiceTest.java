package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Book;
import com.example.service.BookService;

@SpringBootTest
@Transactional  //默认事务回滚
@Rollback(value = false)    //表示不回滚
public class ServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    void testSave() {
        bookService.save(new Book(22, "zhangsan222", "zhangsan222", "zhangsan222"));
    }
}
