package com.url.edustatistics;

/**
 * @author xidazhen
 * @date 2022/10/10 - 22:26
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 定时任务注解
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.url"})
@MapperScan("com.url.edustatistics.mapper")
public class ServiceStatisticsApp8008 {

    public static void main(String[] args) {
        SpringApplication.run(ServiceStatisticsApp8008.class, args);
    }
}