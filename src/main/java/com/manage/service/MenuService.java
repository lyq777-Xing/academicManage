package com.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.Menu;

import java.util.List;

/**
 * @author lyq
 * @time 2024/3/25 15:18
 */
public interface MenuService extends IService<Menu>{
    /**
     * 根据用户id查询菜单列表
     * @param userId 用户id
     * @return List<Menu>
     */
    List<Menu> findByUserId(Integer userId);
}
