package com.manage.security;

import cn.hutool.json.JSONUtil;
import com.manage.common.ResponseResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lyq
 * @time 2024/1/26 20:19
 */

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
//    //跳转的url
//    private String url;
//
//    //是否重定向
//    private boolean isRedirect;
//
//    public MyAuthenticationFailureHandler(String url, boolean isRedirect) {
//        this.url = url;
//        this.isRedirect = isRedirect;
//    }
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        if (isRedirect){
//            //重定向
//            response.sendRedirect(url);
//        }else {
//            //转发
//            request.getRequestDispatcher(url).forward(request,response);
//        }
//    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseResult<Object> result = new ResponseResult<>();
        result.Error("登录失败");
        response.getWriter().write(JSONUtil.toJsonStr(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
