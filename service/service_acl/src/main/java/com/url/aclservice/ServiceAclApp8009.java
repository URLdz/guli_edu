package com.url.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xidazhen
 * @date 2022/10/12 - 15:57
 */

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.url")
@MapperScan("com.url.aclservice.mapper")
public class ServiceAclApp8009 {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApp8009.class, args);
    }

}
