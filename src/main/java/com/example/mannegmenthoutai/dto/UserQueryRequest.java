package com.example.mannegmenthoutai.dto;

import lombok.Data;

@Data
public class UserQueryRequest {
    private String username;  // 用户名搜索
    private String email;     // 邮箱搜索
    private Integer pageNum = 1;  // 页码，默认第1页
    private Integer pageSize = 10; // 每页大小，默认10条
} 