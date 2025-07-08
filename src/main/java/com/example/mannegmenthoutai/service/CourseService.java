package com.example.mannegmenthoutai.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mannegmenthoutai.dto.*;
import com.example.mannegmenthoutai.mapper.CourseMapper;
import com.example.mannegmenthoutai.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;

    public CoursePageResponse getCourseList(CoursePageRequest request) {
        Page<Course> page = new Page<>(request.getPageNum(), request.getPageSize());
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(request.getCourseName())) {
            queryWrapper.like("course_name", request.getCourseName());
        }
        if (StringUtils.hasText(request.getTeacher())) {
            queryWrapper.like("teacher", request.getTeacher());
        }
        if (StringUtils.hasText(request.getStatus())) {
            queryWrapper.eq("status", request.getStatus());
        }
        queryWrapper.orderByDesc("course_id");
        IPage<Course> coursePage = courseMapper.selectPage(page, queryWrapper);
        CoursePageResponse response = new CoursePageResponse();
        response.setTotal(coursePage.getTotal());
        response.setSize(coursePage.getSize());
        response.setCurrent(coursePage.getCurrent());
        response.setPages(coursePage.getPages());
        response.setRecords(coursePage.getRecords());
        return response;
    }

    public CourseAddResponse addCourse(CourseRequest request) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_name", request.getCourseName());
        if (courseMapper.selectCount(queryWrapper) > 0) {
            throw new RuntimeException("课程名称已存在");
        }
        Course newCourse = new Course();
        newCourse.setCourseName(request.getCourseName());
        newCourse.setDescription(request.getDescription());
        newCourse.setTeacher(request.getTeacher());
        newCourse.setCoverUrl(request.getCoverUrl());
        newCourse.setStatus(request.getStatus());
        courseMapper.insert(newCourse);
        return new CourseAddResponse(newCourse);
    }

    public CourseUpdateResponse updateCourse(Integer courseId, CourseRequest request) {
        Course existingCourse = courseMapper.selectById(courseId);
        if (existingCourse == null) {
            throw new RuntimeException("课程不存在, ID: " + courseId);
        }
        if (request.getCourseName() != null && !request.getCourseName().equals(existingCourse.getCourseName())) {
            QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("course_name", request.getCourseName()).ne("course_id", courseId);
            if (courseMapper.selectCount(queryWrapper) > 0) {
                throw new RuntimeException("课程名称已被其他课程使用");
            }
        }
        existingCourse.setCourseName(request.getCourseName());
        existingCourse.setDescription(request.getDescription());
        existingCourse.setTeacher(request.getTeacher());
        existingCourse.setCoverUrl(request.getCoverUrl());
        existingCourse.setStatus(request.getStatus());
        courseMapper.updateById(existingCourse);
        return new CourseUpdateResponse(existingCourse);
    }

    public void deleteCourse(Integer courseId) {
        if (courseMapper.selectById(courseId) == null) {
            throw new RuntimeException("要删除的课程不存在, ID: " + courseId);
        }
        courseMapper.deleteById(courseId);
    }
} 