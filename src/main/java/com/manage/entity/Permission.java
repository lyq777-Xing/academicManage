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
 * @time 2024/1/26 11:55
 */

@TableName("permission")
@Data
public class Permission implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("url")
    private String url;
    @TableField("method")
    private String method;
    @TableField("type")
    private String type;
    @TableField("description")
    private String description;
    @TableField("pid")
    private Integer pid;
    @TableField("status")
    private Integer status;
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @TableField("create_by")
    private String createBy;
}
