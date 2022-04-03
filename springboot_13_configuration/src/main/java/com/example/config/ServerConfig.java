package com.example.config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

//开启对当前bean的属性校验
@Validated
@Component
@Data
@ConfigurationProperties(prefix = "servers")
public class ServerConfig {
    private String ipAddress;
    /**
     * Description:
     * Binding to target org.springframework.boot.context.properties.bind.BindException: Failed to bind properties
     * under 'servers' to com.example.config.ServerConfig failed:
     *     Property: servers.port
     *     Value: 52
     *     Origin: class path resource [application.yml] - 3:9
     *     Reason: 最小值不能超过6666
     *
     * Action:
     * Update your application's configuration
     */
    //对该属性进行校验
    @Max(value = 8888, message = "最大值不能超过8888")
    @Min(value = 6666, message = "最小值不能超过6666")
    private Integer port;
    private Long timeout;

    //默认值是毫秒,可以自定义
    @DurationUnit(ChronoUnit.HOURS)
    private Duration serverTimeOut;

    //存储大小,默认是B
    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize dataSize;
}
