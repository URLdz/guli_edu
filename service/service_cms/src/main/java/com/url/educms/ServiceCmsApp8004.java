package com.url.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author xidazhen
 * @date 2022/10/3 - 18:23
 */
@SpringBootApplication
@MapperScan("com.url.educms.mapper")
@ComponentScan(basePackages = {"com.url"})
public class ServiceCmsApp8004 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsApp8004.class,args);
    }
}
