package com.example.mannegmenthoutai.dto;

import lombok.Data;

@Data
public class CourseRequest {
    private String courseName;
    private String description;
    private String teacher;
    private String coverUrl;
    private String status;
} 