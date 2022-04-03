package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Book;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
