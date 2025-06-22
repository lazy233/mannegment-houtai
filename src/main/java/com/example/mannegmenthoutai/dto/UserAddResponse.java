package com.example.mannegmenthoutai.dto;

import com.example.mannegmenthoutai.model.User;
import lombok.Data;

@Data
public class UserAddResponse {
    private User user;
    private String operationType;
    private Boolean success;
    private String additionalInfo;
    
    public UserAddResponse(User user) {
        this.user = user;
        this.operationType = "ADD_USER";
        this.success = true;
        this.additionalInfo = "用户添加成功，ID: " + user.getUserId();
    }
    
    public UserAddResponse(User user, String additionalInfo) {
        this.user = user;
        this.operationType = "ADD_USER";
        this.success = true;
        this.additionalInfo = additionalInfo;
    }
} 