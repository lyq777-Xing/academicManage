package com.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyq
 * @time 2024/1/26 13:56
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test...";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(String username,String password){
        return "login...";
    }

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "index...";
    }

    @RequestMapping("/loginFail")
    @ResponseBody
    public String loginFail(){
        return "loginFail...";
    }


}
