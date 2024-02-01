package com.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.Role;
import com.manage.mapper.RoleMapper;
import com.manage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lyq
 * @time 2024/1/26 12:12
 */

@Service
@Transactional
public class IRoleService extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRolesByUserId(Integer userId) {
        return roleMapper.findRolesByUserId(userId);
    }
}
