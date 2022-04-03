package com.example.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.BookMapper;
import com.example.domain.Book;
import com.example.service.BookService;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Override
    public boolean saveBook(Book book) {
        return save(book);
    }
}
