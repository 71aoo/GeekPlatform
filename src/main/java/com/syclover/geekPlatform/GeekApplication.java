package com.syclover.geekPlatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.syclover.geekPlatform.dao")
public class GeekApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeekApplication.class, args);
    }

}
