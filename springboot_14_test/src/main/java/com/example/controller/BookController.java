package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Book;

@RestController
@RequestMapping("/books")
public class BookController {
    @GetMapping
    public String getById() {
        System.out.println("getById is running...");
        return "springboot";
    }

    @GetMapping("/getBook")
    public Book getBook() {
        return new Book(1, "test", "test", "test");
    }
}
