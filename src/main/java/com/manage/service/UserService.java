package com.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.Role;
import com.manage.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @author lyq
 * @time 2024/1/24 14:54
 */

public interface UserService extends IService<User> {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(Integer id);


    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户id查询权限列表
     * @param userId
     * @return
     */
    String getAuthorityInfo(Integer userId);

}
