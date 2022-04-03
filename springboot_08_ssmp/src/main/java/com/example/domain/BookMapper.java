package com.example.domain;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
