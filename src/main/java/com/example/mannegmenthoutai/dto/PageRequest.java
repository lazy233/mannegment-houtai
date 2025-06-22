package com.example.mannegmenthoutai.dto;

import lombok.Data;

@Data
public class PageRequest {
    private Integer pageNum = 1;  // 页码，默认第1页
    private Integer pageSize = 10; // 每页大小，默认10条
    private String username; // 用户名，用于搜索

} 