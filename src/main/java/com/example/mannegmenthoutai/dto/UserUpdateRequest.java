package com.example.mannegmenthoutai.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private Integer userId;
    private String username;
    private String email;
    private String password;  // 可选，如果为空则不修改密码
} 