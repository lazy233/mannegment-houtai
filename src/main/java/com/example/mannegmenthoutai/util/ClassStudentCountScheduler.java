package com.example.mannegmenthoutai.util;

import com.example.mannegmenthoutai.mapper.UserMapper;
import com.example.mannegmenthoutai.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ClassStudentCountScheduler {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClassMapper classMapper;

    // 每小时统计一次
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void updateClassStudentCount() {
        List<Map<String, Object>> counts = userMapper.countUsersByClassId();
        for (Map<String, Object> entry : counts) {
            Integer classId = (Integer) entry.get("class_id");
            Long count = (entry.get("count") instanceof Long) ? (Long) entry.get("count") : Long.valueOf(entry.get("count").toString());
            classMapper.updateStudentCount(classId, count.intValue());
        }
        System.out.println("已统计并更新所有班级人数");
    }
} 