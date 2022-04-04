package com.example.service;

import com.example.domain.Book;

public interface BookService {
    public Book getById(Integer id);

    public boolean updateById(Book book);

    public boolean deleteById(Integer id);
}
