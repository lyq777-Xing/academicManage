package com.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取角色id和角色名列表
     * @return Map<String, String>
     */
    ArrayList<HashMap<String, String>> getRoleIdAndNameList();

    /**
     * 查询所有角色列表（分页）
     * @return Page<Role>
     */
    IPage<Role> findAllRolesPage(Integer pageNum, Integer pageSize, String query);
}
