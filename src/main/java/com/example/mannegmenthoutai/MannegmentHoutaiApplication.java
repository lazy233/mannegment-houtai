package com.example.mannegmenthoutai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mannegmenthoutai.mapper")
public class MannegmentHoutaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MannegmentHoutaiApplication.class, args);
    }

}
