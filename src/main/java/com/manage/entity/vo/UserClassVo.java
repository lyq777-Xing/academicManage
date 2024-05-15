package com.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lyq
 * @time 2024/4/10 20:35
 */

@TableName("user_class")
@Data
public class UserClassVo implements Serializable {
    @TableField("user_id")
    private Integer userId;
    @TableField("class_id")
    private Integer classId;
}
