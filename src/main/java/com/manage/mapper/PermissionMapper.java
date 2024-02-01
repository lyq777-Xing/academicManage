package com.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lyq
 * @time 2024/1/26 13:14
 */

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户id查询权限
     * @param userId
     * @return List<Permission>
     */
    List<Permission> findByUserId(Integer userId);
}
