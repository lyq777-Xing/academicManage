package com.manage.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * @author lyq
 * @time 2024/1/31 19:55
 */
public class MyLogoutHandler implements LogoutHandler {
    /**
     * 退出时处理逻辑
     * @param request 请求
     * @param response 响应
     * @param authentication 认证对象 要退出登录的用户主体对象
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        一般做额外处理 比如保存会话中的某些attribute到数据库
//        比如保存会话中的某些数据到文件
//        比如记录日记数据信息
        System.out.println("MyLogoutHandler....");
    }
}
