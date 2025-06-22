package com.example.mannegmenthoutai.dto;

import com.example.mannegmenthoutai.model.Clazz;
import lombok.Data;
import java.util.List;

@Data
public class ClassPageResponse {
    private long total;
    private long size;
    private long current;
    private long pages;
    private List<Clazz> records;
} 