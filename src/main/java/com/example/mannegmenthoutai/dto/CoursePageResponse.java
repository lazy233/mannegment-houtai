package com.example.mannegmenthoutai.dto;

import com.example.mannegmenthoutai.model.Course;
import lombok.Data;
import java.util.List;

@Data
public class CoursePageResponse {
    private long total;
    private long size;
    private long current;
    private long pages;
    private List<Course> records;
} 