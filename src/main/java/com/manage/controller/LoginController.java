package com.manage.controller;

import com.manage.common.ResponseResult;
import com.manage.entity.User;
import com.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @time 2024/3/24 20:07
 */

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseResult<Object> Login(String username, String password,String rememberMe) {
        ResponseResult<Object> result = new ResponseResult<>();
        User byUsername = userService.findByUsername(username);
        if(byUsername == null) {
            result.Error("用户名不存在");
            result.setData(null);
            return result;
        }
        result.Success("登录成功!");
        result.setData(byUsername);
        return result;
    }
}
