package com.example.mannegmenthoutai.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@TableName("users")
@Getter @Setter
public class User {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String email;

    private String password;

    @TableField("class_id")
    private Integer classId;

    @TableField("created_at")
    private Date createdAt;

    @TableField("updated_at")
    private Date updatedAt;
}
