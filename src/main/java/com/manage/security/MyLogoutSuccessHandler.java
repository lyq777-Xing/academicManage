package com.manage.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

/**
 * @author lyq
 * @time 2024/1/31 19:48
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private String url;
    private boolean isRedirect;
    public MyLogoutSuccessHandler(String url, boolean isRedirect) {
        this.url = url;
        this.isRedirect = isRedirect;
    }

    /**
     * 退出成功后的处理逻辑
     * @param request 请求
     * @param response 响应
     * @param authentication 认证对象 要退出登录的用户主体对象
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        request.getSession().invalidate(); //清空session会话
        authentication.setAuthenticated(false); //设置认证对象为未认证状态
        if (isRedirect){
            //重定向
            response.sendRedirect(url);
        }else {
            //转发
            request.getRequestDispatcher(url).forward(request,response);
        }
    }
}
