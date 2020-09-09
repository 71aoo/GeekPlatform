package com.syclover.geekPlatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.syclover.geekPlatform.dao")
@EnableAsync
public class GeekApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeekApplication.class, args);
    }

}
