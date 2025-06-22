package com.example.mannegmenthoutai.dto;

import com.example.mannegmenthoutai.model.User;
import lombok.Data;

@Data
public class UserUpdateResponse {
    private User user;
    private String operationType;
    private Boolean success;
    private String additionalInfo;
    private String updateTime;
    
    public UserUpdateResponse(User user) {
        this.user = user;
        this.operationType = "UPDATE_USER";
        this.success = true;
        this.additionalInfo = "用户更新成功，ID: " + user.getUserId();
        this.updateTime = user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : "";
    }
    
    public UserUpdateResponse(User user, String additionalInfo) {
        this.user = user;
        this.operationType = "UPDATE_USER";
        this.success = true;
        this.additionalInfo = additionalInfo;
        this.updateTime = user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : "";
    }
} 