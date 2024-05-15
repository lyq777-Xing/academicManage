package com.manage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lyq
 * @time 2024/4/27 23:08
 */

@TableName("stu_course")
@Data
public class StuCourse implements Serializable {
    @TableField("stu_id")
    private Integer stuId;
    @TableField("course_id")
    private Integer courseId;
    @TableField("score")
    private double score;
    @TableField("teacher_id")
    private Integer teacherId;
    @TableField(exist = false)
    private String teacherName;
    @TableField(exist = false)
    private String courseName;
}
