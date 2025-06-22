package com.example.mannegmenthoutai.dto;

import com.example.mannegmenthoutai.model.Clazz;
import lombok.Data;

@Data
public class ClassAddResponse {
    private Clazz clazz;
    private String operationType;
    private Boolean success;
    private String additionalInfo;

    public ClassAddResponse(Clazz clazz) {
        this.clazz = clazz;
        this.operationType = "ADD_CLASS";
        this.success = true;
        this.additionalInfo = "班级创建成功, ID: " + clazz.getClassId();
    }
} 