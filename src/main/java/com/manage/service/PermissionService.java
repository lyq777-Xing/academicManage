package com.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.Permission;

import java.util.List;

/**
 * @author lyq
 * @time 2024/1/26 13:15
 */

public interface PermissionService extends IService<Permission> {

    /**
     * 根据用户id查询权限列表 非树形 用于登录时认证
     * @param userId 用户id
     * @return List<Permission>
     */
    List<Permission> findByUserId(Integer userId);

    /**
     * 根据用户id查询权限列表 树形 用于前端展示
     * @param userId 用户id
     * @return List<Permission>
     */
    List<Permission> findTreeByUserId(Integer userId);
}
