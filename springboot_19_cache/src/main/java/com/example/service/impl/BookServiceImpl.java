package com.example.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.domain.Book;
import com.example.mapper.BookMapper;
import com.example.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    //模拟缓存
    Map<Integer, Book> cache = new HashMap<>();

    /**
     * 使用自定义的缓存
     * @param id
     * @return
     */
//    @Override
//    public Book getById(Integer id) {
//        //查询缓存中是否存在数据
//        Book book = cache.get(id);
//        if (book != null) {
//            log.info("BookId="+id + "使用了缓存");
//            return book;
//        }
//        Book select = bookMapper.selectById(id);
//        log.info("缓存中没有BookId="+id + "，从数据库查询并放入缓存");
//        cache.put(select.getId(), select);
//        return select;
//    }

    /**
     * 使用springboot提供的缓存
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "cacheSpace",key = "#id")
    public Book getById(Integer id) {
        Book select = bookMapper.selectById(id);
        return select;
    }
}
