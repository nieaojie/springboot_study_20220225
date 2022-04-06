package com.example.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.quartz.MyQuartz;

/**
 * 2.设定一个配置类
 */
@Configuration
public class QuartzConfig {

    /**
     * 工作明细
     * @return
     */
    @Bean
    public JobDetail printJobDetail() {
        //绑定具体的工作
        return JobBuilder
                .newJob(MyQuartz.class)
                .storeDurably()
                .build();
    }

    /**
     * 触发器
     * @return
     */
    @Bean
    public Trigger printJobTrigger() {
        //绑定对应的工作明细
        ScheduleBuilder<? extends Trigger> schedBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        return TriggerBuilder
                .newTrigger()
                .forJob(printJobDetail())
                .withSchedule(schedBuilder)
                .build();
    }
}
