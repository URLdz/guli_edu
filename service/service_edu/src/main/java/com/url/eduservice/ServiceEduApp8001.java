package com.url.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xidazhen
 * @date 2022/9/17 - 19:34
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@ComponentScan("com.url")
public class ServiceEduApp8001 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApp8001.class,args);
    }
}
