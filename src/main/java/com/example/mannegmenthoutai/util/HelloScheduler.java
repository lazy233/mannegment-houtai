package com.example.mannegmenthoutai.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HelloScheduler {
    // 每隔1小时执行一次
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void printHello() {
        System.out.println("hello");
    }
} 