package com.manage.controller;

import com.manage.common.ResponseResult;
import com.manage.entity.Menu;
import com.manage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lyq
 * @time 2024/3/25 15:38
 */

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult<Object> getMenuList(Integer userId) {
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<Menu> menuList = menuService.findByUserId(userId);
            result.Success("查询成功");
            result.setData(menuList);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.Error("查询失败");
            return result;
        }
    }
}
