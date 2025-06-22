package com.example.mannegmenthoutai.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mannegmenthoutai.dto.*;
import com.example.mannegmenthoutai.mapper.ClassMapper;
import com.example.mannegmenthoutai.model.Clazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ClassService {

    @Autowired
    private ClassMapper classMapper;

    public ClassPageResponse getClassList(ClassPageRequest request) {
        Page<Clazz> page = new Page<>(request.getPageNum(), request.getPageSize());
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();

        if (StringUtils.hasText(request.getClassName())) {
            queryWrapper.like("class_name", request.getClassName());
        }
        queryWrapper.orderByDesc("class_id");

        IPage<Clazz> classPage = classMapper.selectPage(page, queryWrapper);

        ClassPageResponse response = new ClassPageResponse();
        response.setTotal(classPage.getTotal());
        response.setSize(classPage.getSize());
        response.setCurrent(classPage.getCurrent());
        response.setPages(classPage.getPages());
        response.setRecords(classPage.getRecords());
        return response;
    }

    public ClassAddResponse addClass(ClassRequest request) {
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_name", request.getClassName());
        if (classMapper.selectCount(queryWrapper) > 0) {
            throw new RuntimeException("班级名称已存在");
        }

        Clazz newClazz = new Clazz();
        newClazz.setClassName(request.getClassName());
        newClazz.setStudentCount(request.getStudentCount());
        newClazz.setClassroom(request.getClassroom());
        
        classMapper.insert(newClazz);
        return new ClassAddResponse(newClazz);
    }

    public ClassUpdateResponse updateClass(Integer classId, ClassRequest request) {
        Clazz existingClazz = classMapper.selectById(classId);
        if (existingClazz == null) {
            throw new RuntimeException("班级不存在, ID: " + classId);
        }

        if (request.getClassName() != null && !request.getClassName().equals(existingClazz.getClassName())) {
            QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("class_name", request.getClassName()).ne("class_id", classId);
            if (classMapper.selectCount(queryWrapper) > 0) {
                throw new RuntimeException("班级名称已被其他班级使用");
            }
        }

        existingClazz.setClassName(request.getClassName());
        existingClazz.setStudentCount(request.getStudentCount());
        existingClazz.setClassroom(request.getClassroom());
        
        classMapper.updateById(existingClazz);
        return new ClassUpdateResponse(existingClazz);
    }

    public void deleteClass(Integer classId) {
        if (classMapper.selectById(classId) == null) {
            throw new RuntimeException("要删除的班级不存在, ID: " + classId);
        }
        classMapper.deleteById(classId);
    }
} 