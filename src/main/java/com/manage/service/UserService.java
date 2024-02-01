package com.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.User;

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
}
