package com.example;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.domain.Book;

@SpringBootTest
class Springboot17MongodbApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        mongoTemplate.save(new Book(1, "zhangsan", "person", "人物"), "book");
    }

    @Test
    void findAll() {
        List<Book> books = mongoTemplate.findAll(Book.class);
        books.stream().forEach(System.out::println);
    }

}
