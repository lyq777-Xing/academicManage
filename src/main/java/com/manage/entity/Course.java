package com.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author lyq
 * @time 2024/4/14 12:27
 */

@TableName("course")
@Data
public class Course implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("code")
    private String code;
    @TableField("introduce")
    private String introduce;
    @TableField("college_id")
    private Integer collegeId;
    @TableField(exist = false)
    private String collegeName;
    @TableField("remark")
    private Integer remark;
    @TableField("category_id")
    private Integer categoryId;
    @TableField(exist = false)
    private String categoryName;
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @TableField(exist = false)
    private ArrayList<User> students;
    @TableField(exist = false)
    private ArrayList<User> teachers;
    @TableField("student_ids")
    private String studentIds;
    @TableField("teacher_ids")
    private String teacherIds;
}

