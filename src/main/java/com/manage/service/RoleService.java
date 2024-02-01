package com.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.Role;

import java.util.List;

/**
 * @author lyq
 * @time 2024/1/26 12:12
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(Integer userId);
}
