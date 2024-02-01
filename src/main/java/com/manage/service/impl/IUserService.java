package com.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.User;
import com.manage.mapper.UserMapper;
import com.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lyq
 * @time 2024/1/24 14:59
 */

@Transactional
@Service
public class IUserService extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

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
}
