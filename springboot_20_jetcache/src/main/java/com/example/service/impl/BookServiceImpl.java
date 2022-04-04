package com.example.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.example.domain.Book;
import com.example.mapper.BookMapper;
import com.example.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    /**
     * @Cached注解可以加在接口方法上也可以加在类方法上，但需要保证是个Spring bean
     * @param id
     * @return
     */
    @Override
    //没有指定name的key:c.e.s.i.BookServiceImpl.getById(Lj.l.Integer;)1
    //@Cached(key = "#id",expire = 20,timeUnit = TimeUnit.SECONDS)
    //指定name
    @Cached(name = "bookCache::", key = "#id", expire = 30, timeUnit = TimeUnit.SECONDS)
    //每隔5s就从数据库获取一次
    //@CacheRefresh(refresh = 5)
    public Book getById(Integer id) {
        Book select = bookMapper.selectById(id);
        return select;
    }

    @Override
    @CacheUpdate(name = "bookCache::", key = "#book.id", value = "#book", condition = "#book.id==1")
    public boolean updateById(Book book) {
        return bookMapper.updateById(book) > 0;
    }

    @Override
    @CacheInvalidate(name = "bookCache::", key = "#id")
    public boolean deleteById(Integer id) {
        return bookMapper.deleteById(id) > 0;
    }
}
