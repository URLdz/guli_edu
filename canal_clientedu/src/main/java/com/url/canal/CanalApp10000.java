package com.url.canal;

import com.url.canal.client.CanalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author xidazhen
 * @date 2022/10/11 - 22:53
 */

@SpringBootApplication
public class CanalApp10000 implements CommandLineRunner {

    @Resource
    private CanalClient canalClient;

    public static void main(String[] args) {
        SpringApplication.run(CanalApp10000.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //项目启动，执行canal客户端监听
        canalClient.run();
    }
}

