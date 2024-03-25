package com.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.Permission;
import com.manage.entity.Role;
import com.manage.entity.User;
import com.manage.mapper.RoleMapper;
import com.manage.mapper.UserMapper;
import com.manage.service.PermissionService;
import com.manage.service.RoleService;
import com.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lyq
 * @time 2024/1/24 14:59
 */

@Transactional
@Service
public class IUserService extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 根据id查询
     * @param id
     * @return User
     */
    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 根据用户名查询
     * @param username
     * @return User
     */
    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        return userMapper.selectOne(userQueryWrapper);
    }

    /**
     * 根据用户id查询权限列表
     * @param userId 用户id
     * @return String
     */
    @Override
    public String getAuthorityInfo(Integer userId) {
        String authority = "";
//        获取用户name
        User user = userMapper.selectById(userId);
        String username = user.getUsername();
        List<Role> roles = roleService.findRolesByUserId(userId);
        if(roles.size() == 1){
            authority = "ROLE_" + roles.get(0).getKey();

        }
        Role role = roles.get(0);
        if(role != null){
            List<Permission> permissions = permissionService.findByUserId(userId);
            for (Permission permission : permissions) {
                authority += "," + permission.getName();
            }
        }
        return authority;
    }

}
