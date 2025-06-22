package com.example.mannegmenthoutai.controller;

import com.example.mannegmenthoutai.dto.*;
import com.example.mannegmenthoutai.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping("/list")
    public ClassPageResponse getClassList(@RequestBody ClassPageRequest request) {
        return classService.getClassList(request);
    }

    @PostMapping("/addClass")
    public ClassAddResponse addClass(@RequestBody ClassRequest request) {
        return classService.addClass(request);
    }

    @PutMapping("/updateClass/{classId}")
    public ClassUpdateResponse updateClass(@PathVariable Integer classId, @RequestBody ClassRequest request) {
        return classService.updateClass(classId, request);
    }

    @DeleteMapping("/deleteClass/{classId}")
    public void deleteClass(@PathVariable Integer classId) {
        classService.deleteClass(classId);
    }
} 