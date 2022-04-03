package com.example.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.domain.Book;

public interface BookService2 {
    Boolean insert(Book book);

    Boolean delete(Integer id);

    Boolean update(Book book);

    Book getById(Integer id);

    List<Book> getAll();

    IPage<Book> getPage(Integer currentPage, int size);

}
