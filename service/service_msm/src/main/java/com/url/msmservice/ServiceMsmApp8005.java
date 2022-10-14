package com.url.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xidazhen
 * @date 2022/10/4 - 20:43
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.url"})
public class ServiceMsmApp8005 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMsmApp8005.class,args);
    }
}
