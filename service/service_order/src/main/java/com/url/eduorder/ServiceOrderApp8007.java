package com.url.eduorder;

/**
 * @author xidazhen
 * @date 2022/10/9 - 22:05
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = {"com.url"})
@MapperScan("com.url.eduorder.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceOrderApp8007 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApp8007.class, args);
    }
}

