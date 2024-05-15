package com.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.Role;
import com.manage.mapper.RoleMapper;
import com.manage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyq
 * @time 2024/1/26 12:12
 */

@Service
@Transactional
public class IRoleService extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户id查询角色
     * @param userId 用户id
     * @return List<Role>
     */
    @Override
    public List<Role> findRolesByUserId(Integer userId) {
        return roleMapper.findRolesByUserId(userId);
    }

    /**
     * 获取角色id和角色名列表
     * @return Map<String, String>
     */
    @Override
    public ArrayList<HashMap<String, String>> getRoleIdAndNameList() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        roleMapper.findAllRoles().forEach(role -> {
            HashMap<String, String> roleIdAndNameMap = new HashMap<>();
            roleIdAndNameMap.put("label",role.getName());
            roleIdAndNameMap.put("value",role.getId().toString());
            list.add(roleIdAndNameMap);
        });
        return list;
    }

    /**
     * 查询所有角色列表（分页）
     * @return Page<Role>
     */
    @Override
    public IPage<Role> findAllRolesPage(Integer pageNum, Integer pageSize, String query) {
        Page<Role> page = new Page<>(pageNum, pageSize);
        return roleMapper.findAllRolesPage(page, query);
    }
}
