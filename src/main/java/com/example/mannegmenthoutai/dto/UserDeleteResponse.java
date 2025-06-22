package com.example.mannegmenthoutai.dto;

import lombok.Data;

@Data
public class UserDeleteResponse {
    private String operationType;
    private Boolean success;
    private String additionalInfo;
    private String deleteTime;
    
    public UserDeleteResponse(Integer userId) {
        this.operationType = "DELETE_USER";
        this.success = true;
        this.additionalInfo = "用户删除成功，ID: " + userId;
        this.deleteTime = new java.util.Date().toString();
    }
    
    public UserDeleteResponse(Integer userId, String additionalInfo) {
        this.operationType = "DELETE_USER";
        this.success = true;
        this.additionalInfo = additionalInfo;
        this.deleteTime = new java.util.Date().toString();
    }
} 