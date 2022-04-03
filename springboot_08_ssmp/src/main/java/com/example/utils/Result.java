package com.example.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: nie
 * @create: 2022-03-01 16:38
 * @description: 封装统一的返回值类型
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean flag;
    private Object data;
    private String msg;

    public Result(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public Result(String msg) {
        this.flag = false;
        this.msg = msg;
    }
}
