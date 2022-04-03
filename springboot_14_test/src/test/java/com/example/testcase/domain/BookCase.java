package com.example.testcase.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "testcase.book")
public class BookCase {
    private Integer id;
    private Integer scopeId1;
    private Integer scopeId2;
    private String name;
    private String name2;
    private String uuid;
    private Long publishTime;

}
