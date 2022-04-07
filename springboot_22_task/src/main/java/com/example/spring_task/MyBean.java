package com.example.spring_task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    /**
     * 表示每隔3s执行一次
     */
    @Scheduled(cron = "0/3 * * * * ?")
    public void run() {
        System.out.println(Thread.currentThread().getName() + "：spring task run ...");
    }
}
