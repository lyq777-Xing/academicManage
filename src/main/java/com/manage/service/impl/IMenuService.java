package com.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.Menu;
import com.manage.mapper.MenuMapper;
import com.manage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyq
 * @time 2024/3/25 15:26
 */

@Transactional
@Service
public class IMenuService extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据用户id查询菜单列表
     * @param userId 用户id
     * @return
     */
    @Override
    public List<Menu> findByUserId(Integer userId) {
        List<Menu> menuList = menuMapper.findByUserId(userId);
        ArrayList<Menu> parents = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getPid() == 0) {
                List<Menu> children = new ArrayList<>();
                for (Menu child : menuList) {
                    if (child.getPid().equals(menu.getId())) {
                        children.add(child);
                    }
                }
                menu.setChildren(children);
                parents.add(menu);
            }
        }
        return parents;
    }
}
