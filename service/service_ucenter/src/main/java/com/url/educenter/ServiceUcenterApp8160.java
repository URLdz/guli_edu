package com.url.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xidazhen
 * @date 2022/10/5 - 15:26
 */
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.url.educenter.mapper")
@SpringBootApplication
@ComponentScan(basePackages = {"com.url"})
public class ServiceUcenterApp8160 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcenterApp8160.class,args);
    }
}
