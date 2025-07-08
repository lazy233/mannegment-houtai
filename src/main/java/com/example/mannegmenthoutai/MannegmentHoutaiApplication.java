package com.example.mannegmenthoutai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.mannegmenthoutai.mapper")
@EnableScheduling
public class MannegmentHoutaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MannegmentHoutaiApplication.class, args);
    }

}
