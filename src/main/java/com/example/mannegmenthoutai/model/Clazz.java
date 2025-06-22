package com.example.mannegmenthoutai.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 班级实体类
 * 使用 Clazz 是为了避免和 Java 的关键字 Class 冲突
 */
@Data
@TableName("classes")
public class Clazz {

    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    @TableField("class_name")
    private String className;

    @TableField("student_count")
    private Integer studentCount;

    private String classroom;
} 