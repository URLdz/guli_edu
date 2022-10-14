package com.url.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xidazhen
 * @date 2022/9/21 - 13:25
 */
@EnableDiscoveryClient
//忽略加载数据库配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.url"})
public class ServiceOssApp8002 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApp8002.class,args);
    }
}

