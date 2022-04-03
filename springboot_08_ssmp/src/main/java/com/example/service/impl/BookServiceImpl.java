package com.example.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Book;
import com.example.domain.BookMapper;
import com.example.service.IBookService;

/**
 * @author: nie
 * @create: 2022-02-28 18:21
 * @description:
 **/
@Service
class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
