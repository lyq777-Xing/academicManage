package com.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lyq
 * @time 2024/1/24 14:44
 */

@TableName("user")
@Data
public class User implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("email")
    private String email;
    @TableField("phone")
    private String phone;
    @TableField("introduce")
    private String introduce;
    @TableField("class_id")
    private Integer classId;
    @TableField(exist = false)
    private String className;
    @TableField("college_id")
    private Integer collegeId;
    @TableField(exist = false)
    private String collegeName;
    @TableField(exist = false)
    private Integer roleId;
    @TableField(exist = false)
    private String roleName;
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @TableField(exist = false)
    private String teacherName;
    @TableField(exist = false)
    private Integer teacherId;
    @TableField(exist = false)
    private double score;
}
