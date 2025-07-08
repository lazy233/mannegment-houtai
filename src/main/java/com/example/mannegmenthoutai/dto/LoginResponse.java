package com.example.mannegmenthoutai.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String message;
    private boolean success;
    private UserInfo userInfo;
    
    @Data
    public static class UserInfo {
        private Integer userId;
        private String username;
        private String email;
        private Integer classId;
    }
} 