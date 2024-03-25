package com.manage.controller;

import com.manage.common.ResponseResult;
import com.manage.entity.User;
import com.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lyq
 * @time 2024/1/29 19:19
 */
@RestController
@RequestMapping("/user")
public class UserController {



    @GetMapping("/getList")
    @PreAuthorize("hasRole('ROLE_SuperAdmin')")
    public ResponseResult<Object> getUsers() {
        ResponseResult<Object> result = new ResponseResult<>();
        result.Success("获取用户列表成功");
        return result;
    }
}
