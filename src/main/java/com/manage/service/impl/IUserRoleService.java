package com.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.vo.UserRoleVo;
import com.manage.mapper.UserRoleMapper;
import com.manage.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lyq
 * @time 2024/4/10 20:50
 */

@Transactional
@Service
public class IUserRoleService extends ServiceImpl<UserRoleMapper, UserRoleVo> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;


    /**
     * 插入用户角色关联
     * @param userRoleVo 用户角色关联
     * @return int
     */
    @Override
    public int insert(UserRoleVo userRoleVo) {
        return userRoleMapper.insert(userRoleVo);
    }

    /**
     * 更新用户角色关联
     * @param userRoleVo 用户角色关联
     * @return int
     */
    @Override
    public int updateUserRole(UserRoleVo userRoleVo) {
        QueryWrapper<UserRoleVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userRoleVo.getUserId());
        return userRoleMapper.update(userRoleVo, queryWrapper);
    }

    /**
     * 根据用户id查询用户角色
     * @param id 用户id
     * @return UserRoleVo
     */
    @Override
    public UserRoleVo findByUserId(Integer id) {
        QueryWrapper<UserRoleVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        return userRoleMapper.selectOne(queryWrapper);
    }
}
