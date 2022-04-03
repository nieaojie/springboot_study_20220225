package com.example.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
@RestControllerAdvice
public class ProjectExceptionAdvice {

    //异常处理器,参数Exception.class表示该方法只处理这样的异常
    @ExceptionHandler(Exception.class)
    public Result doException(Exception e) {
        //不要忘了把异常输出到控制台
        e.printStackTrace();
        return new Result("服务器故障，请稍后再试");
    }
}
