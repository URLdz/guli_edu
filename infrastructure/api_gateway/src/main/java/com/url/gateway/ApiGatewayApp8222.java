package com.url.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xidazhen
 * @date 2022/10/12 - 12:32
 */
@SpringBootApplication
@EnableDiscoveryClient

public class ApiGatewayApp8222 {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApp8222.class, args);
    }
}