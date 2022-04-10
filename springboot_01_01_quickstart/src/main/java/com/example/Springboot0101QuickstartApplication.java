package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.controller.BookController;
import com.example.entity.User;

//该注解有注解@ComponentScan，意味着会扫描该启动类所在包下的所有bean.
@SpringBootApplication
public class Springboot0101QuickstartApplication {

    public static void main(String[] args) {
        //1.run方法就是启动了一个容器
        //ConfigurableApplicationContext可配置的上下文，就是一个容器。
        ConfigurableApplicationContext ctx = SpringApplication.run(Springboot0101QuickstartApplication.class,
                                                                   args);
        //从ctx容器中可以获取bean
        BookController bookControllerBean = ctx.getBean(BookController.class);
        System.out.println("bookControllerBean--->" + bookControllerBean);
        //另外定义一个啥也没有的User组件一样可以获得
        User userBean = ctx.getBean(User.class);
        System.out.println("userBean--->" + userBean);

        //输出的打印信息
        //bookControllerBean--->com.example.controller.BookController@1e6b9a95
        //userBean--->com.example.entity.User@4d6f623d
    }
}
