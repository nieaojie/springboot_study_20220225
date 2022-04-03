package com.example.dao.impl;

import org.springframework.stereotype.Repository;

import com.example.dao.BookDao;

/**
 * @author: nie
 * @create: 2022-02-28 15:34
 * @description:
 **/
@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("save...");
    }
}
