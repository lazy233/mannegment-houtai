package com.example.mannegmenthoutai.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserPageResponse {
    private List<UserInfo> records;  // 用户列表
    private long total;              // 总记录数
    private long size;               // 每页大小
    private long current;            // 当前页码
    private long pages;              // 总页数
    
    @Data
    public static class UserInfo {
        private Integer userId;
        private String username;
        private String email;
        private String createdAt;
        private String updatedAt;
        private Integer classId;
    }
} 