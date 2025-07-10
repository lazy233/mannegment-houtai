package com.example.mannegmenthoutai.dto;

import com.example.mannegmenthoutai.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class CourseUpdateResponse {
    private Course course;
} 