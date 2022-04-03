package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.config.ServerConfig;

@SpringBootApplication
public class Springboot13ConfigurationApplication {

    //第一种：自己定义
//    @Bean
//    public DruidDataSource druidDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        return dataSource;
//    }


    //第二种：注入进来
    @Bean
    @ConfigurationProperties(prefix = "datasource") //此处的datasource必须小写，或者用-连接，不能写成dataSource
    public DruidDataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }



    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Springboot13ConfigurationApplication.class,
                                                                   args);
        ServerConfig bean = ctx.getBean(ServerConfig.class);
        System.out.println(bean);
        DruidDataSource druidDataSource = ctx.getBean(DruidDataSource.class);
        String driverClassName = druidDataSource.getDriverClassName();
        System.out.println(driverClassName);
    }

}
