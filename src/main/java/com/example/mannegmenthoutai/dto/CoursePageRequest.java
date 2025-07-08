package com.example.mannegmenthoutai.dto;

import lombok.Data;

@Data
public class CoursePageRequest {
    private int pageNum = 1;
    private int pageSize = 10;
    private String courseName;
    private String teacher;
    private String status;
} 