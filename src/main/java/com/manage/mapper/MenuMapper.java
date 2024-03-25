package com.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lyq
 * @time 2024/3/25 15:20
 */

@Mapper
public interface MenuMapper extends BaseMapper<Menu>{
    /**
     * 根据用户id查询菜单列表
     * @param userId 用户id
     * @return List<Menu>
     */
    List<Menu> findByUserId(Integer userId);
}
