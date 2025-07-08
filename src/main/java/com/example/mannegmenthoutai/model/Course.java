package com.example.mannegmenthoutai.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("courses")
public class Course {
    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    @TableField("course_name")
    private String courseName;

    private String description;

    private String teacher;

    @TableField("cover_url")
    private String coverUrl;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    private String status;
} 