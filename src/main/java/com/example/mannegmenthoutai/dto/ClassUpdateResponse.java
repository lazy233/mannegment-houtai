package com.example.mannegmenthoutai.dto;

import com.example.mannegmenthoutai.model.Clazz;
import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ClassUpdateResponse {
    private Clazz clazz;
    private String operationType;
    private Boolean success;
    private String additionalInfo;
    private String updateTime;

    public ClassUpdateResponse(Clazz clazz) {
        this.clazz = clazz;
        this.operationType = "UPDATE_CLASS";
        this.success = true;
        this.additionalInfo = "班级更新成功, ID: " + clazz.getClassId();
        this.updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
} 