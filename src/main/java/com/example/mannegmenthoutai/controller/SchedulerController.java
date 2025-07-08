package com.example.mannegmenthoutai.controller;

import com.example.mannegmenthoutai.util.HelloScheduler;
import com.example.mannegmenthoutai.util.ClassStudentCountScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scheduler")
public class SchedulerController {

    @Autowired
    private HelloScheduler helloScheduler;

    @Autowired
    private ClassStudentCountScheduler classStudentCountScheduler;

    @PostMapping("/trigger-hello")
    public String triggerHello() {
        helloScheduler.printHello();
        return "手动触发成功";
    }

    @PostMapping("/trigger-class-student-count")
    public String triggerClassStudentCount() {
        classStudentCountScheduler.updateClassStudentCount();
        return "班级人数统计已手动触发";
    }
} 