package com.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lyq
 * @time 2024/4/27 23:21
 */

@Data
@TableName("user_role")
public class UserRoleVo implements Serializable {
    @TableField("user_id")
    private Integer userId;
    @TableField("role_id")
    private Integer roleId;
}
