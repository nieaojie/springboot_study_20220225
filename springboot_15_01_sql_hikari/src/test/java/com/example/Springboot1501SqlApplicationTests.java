package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Book;
import com.example.service.BookService;

@SpringBootTest
class Springboot1501SqlApplicationTests {

    @Autowired
    private BookService bookService;

    @Test
    void contextLoads() {
        bookService.saveBook(new Book(5, "testsql", "testsql", "testsql"));
    }

}
