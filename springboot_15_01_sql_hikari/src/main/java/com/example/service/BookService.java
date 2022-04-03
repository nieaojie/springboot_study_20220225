package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Book;

public interface BookService extends IService<Book> {
    public boolean saveBook(Book book);
}
