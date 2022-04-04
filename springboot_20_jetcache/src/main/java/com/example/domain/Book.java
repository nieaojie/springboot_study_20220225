package com.example.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Book implements Serializable { //需要实现序列化接口，因为需要存到redis中
    private Integer id;
    private String type;
    private String name;
    private String description;
}
