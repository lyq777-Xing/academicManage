package com.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.Permission;
import com.manage.mapper.PermissionMapper;
import com.manage.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lyq
 * @time 2024/1/26 13:15
 */

@Transactional
@Service
public class IPermissionService extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findByUserId(Integer userId) {
        return permissionMapper.findByUserId(userId);
    }
}
