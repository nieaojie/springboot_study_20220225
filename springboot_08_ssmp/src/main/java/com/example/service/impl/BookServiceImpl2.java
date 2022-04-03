package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Book;
import com.example.domain.BookMapper;
import com.example.service.BookService2;

@Service
public class BookServiceImpl2 implements BookService2 {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Boolean insert(Book book) {
        return bookMapper.insert(book) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return bookMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean update(Book book) {
        return bookMapper.update(book, null) > 0;
    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.selectById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookMapper.selectList(null);
    }

    @Override
    public IPage<Book> getPage(Integer currentPage, int size) {
        return bookMapper.selectPage(new Page<>(currentPage, size), null);
    }
}
