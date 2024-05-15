package com.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.vo.UserRoleVo;

/**
 * @author lyq
 * @time 2024/4/10 20:50
 */
public interface UserRoleService extends IService<UserRoleVo> {

    /**
     * 插入用户角色关联
     * @param userRoleVo 用户角色关联
     * @return int
     */
    int insert(UserRoleVo userRoleVo);

    /**
     * 更新用户角色关联
     * @param userRoleVo 用户角色关联
     * @return int
     */
    int updateUserRole(UserRoleVo userRoleVo);

    /**
     * 根据用户id查询用户角色
     * @param id 用户id
     * @return UserRoleVo
     */
    UserRoleVo findByUserId(Integer id);
}
