package com.example.mannegmenthoutai.dto;

import lombok.Data;

@Data
public class ClassPageRequest {
    private int pageNum = 1;
    private int pageSize = 10;
    private String className;
} 