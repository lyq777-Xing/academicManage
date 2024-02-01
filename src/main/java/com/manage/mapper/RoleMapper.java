package com.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lyq
 * @time 2024/1/26 12:11
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(Integer userId);
}
