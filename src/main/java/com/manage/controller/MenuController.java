package com.manage.controller;

import com.manage.common.ResponseResult;
import com.manage.entity.Menu;
import com.manage.entity.User;
import com.manage.service.MenuService;
import com.manage.service.UserService;
import com.manage.utils.GetSecurityUserUtil;
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

    @Autowired
    private GetSecurityUserUtil getSecurityUserUtil;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult<Object> getMenuList() {

        ResponseResult<Object> result = new ResponseResult<>();
        try{
            String username = getSecurityUserUtil.getCurrentUser();
            User user = userService.findByUsername(username);
            List<Menu> menuList = menuService.findByUserId(user.getId());
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
