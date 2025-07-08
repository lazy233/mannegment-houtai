package com.example.mannegmenthoutai.controller;

import com.example.mannegmenthoutai.dto.*;
import com.example.mannegmenthoutai.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/list")
    public CoursePageResponse getCourseList(@RequestBody CoursePageRequest request) {
        return courseService.getCourseList(request);
    }

    @PostMapping("/addCourse")
    public CourseAddResponse addCourse(@RequestBody CourseRequest request) {
        return courseService.addCourse(request);
    }

    @PutMapping("/updateCourse/{courseId}")
    public CourseUpdateResponse updateCourse(@PathVariable Integer courseId, @RequestBody CourseRequest request) {
        return courseService.updateCourse(courseId, request);
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public void deleteCourse(@PathVariable Integer courseId) {
        courseService.deleteCourse(courseId);
    }
} 