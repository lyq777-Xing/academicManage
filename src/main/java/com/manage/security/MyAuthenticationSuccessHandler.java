package com.manage.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * @author lyq
 * @time 2024/1/26 20:03
 * 认证成功后，处理代码逻辑
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    //跳转的url
    private String url;

    //是否重定向
    private boolean isRedirect;

    public MyAuthenticationSuccessHandler(String url, boolean isRedirect) {
        this.url = url;
        this.isRedirect = isRedirect;
    }

    /**
     * 认证成功后具体地执行代码逻辑
     * @param request 请求
     * @param response 响应
     * @param authentication 认证对象 认证成功后的主体对象 包含登录用户的个人信息和权限列表
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (isRedirect){
            //重定向
            response.sendRedirect(url);
        }else {
            //转发
            request.getRequestDispatcher(url).forward(request,response);
        }
    }
}
