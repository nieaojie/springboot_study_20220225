package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Book;

@Mapper
public interface BookDao extends BaseMapper<Book> {

}
