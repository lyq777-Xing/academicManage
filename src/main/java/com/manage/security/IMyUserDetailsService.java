package com.manage.security;

import com.manage.entity.Permission;
import com.manage.entity.Role;
import com.manage.entity.User;
import com.manage.mapper.UserMapper;
import com.manage.service.PermissionService;
import com.manage.service.RoleService;
import com.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyq
 * @time 2024/1/26 13:40
 */

@Component
public class IMyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 根据用户名查询用户信息 用于登录时认证 根据用户名查询用户对象和权限列表
     * @param username 用户名
     * @return UserDetails 接口类对象
     * @throws UsernameNotFoundException 用户名不存在异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        根据用户名查询用户对象
        User user = userService.findByUsername(username);
//        判断用户名是否存在
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

//        查询已登录用户的权限列表
//        查询角色
        List<Role> roles = roleService.findRolesByUserId(user.getId());
//        查询权限
        List<Permission> permissions = permissionService.findByUserId(user.getId());
//        定义一个字符串泛型的list集合
        List<String> authorities = new ArrayList<>();
        for (Role role : roles) {
//            添加角色 角色名称纳入权限管理，必须增加ROLE_前缀
            authorities.add("ROLE_" + role.getName());
        }
        for (Permission permission : permissions) {
//            添加权限
            authorities.add(permission.getName());
        }

//        返回UserDetails接口类对象
        return
            new org.springframework.security.core.userdetails.User(
                    user.getUsername(),  //用户名
                    user.getPassword(),  //密码
                    AuthorityUtils.createAuthorityList(authorities) //权限列表 NO_AUTHORITIES表示没有任何权限 通过方法将字符串泛型的list集合转换为字符串数组
            );
    }
}
