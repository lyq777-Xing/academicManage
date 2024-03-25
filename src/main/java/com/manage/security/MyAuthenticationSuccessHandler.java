package com.manage.security;

import cn.hutool.json.JSONUtil;
import com.manage.common.ResponseResult;
import com.manage.entity.AuthenticationToken;
import com.manage.utils.JwtUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

/**
 * @author lyq
 * @time 2024/1/26 20:03
 * 认证成功后，处理代码逻辑
 */

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils;

//    //跳转的url
//    private String url;
//
//    //是否重定向
//    private boolean isRedirect;
//
//    public MyAuthenticationSuccessHandler(String url, boolean isRedirect) {
//        this.url = url;
//        this.isRedirect = isRedirect;
//    }
//
//    /**
//     * 认证成功后具体地执行代码逻辑
//     * @param request 请求
//     * @param response 响应
//     * @param authentication 认证对象 认证成功后的主体对象 包含登录用户的个人信息和权限列表
//     * @throws IOException IO异常
//     * @throws ServletException Servlet异常
//     */
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        if (isRedirect){
//            //重定向
//            response.sendRedirect(url);
//        }else {
//            //转发
//            request.getRequestDispatcher(url).forward(request,response);
//        }
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
//        通过响应输出流，向客户端输出一个友好结果
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        获取认证信息
        AuthenticationToken token = new AuthenticationToken();
//        生成token的值
//        生成jwt 放入请求头中
        String jwt = jwtUtils.generateToken(authentication.getName());
        response.setHeader(jwtUtils.getHeader(),jwt);
        token.setToken(jwt);
        token.setAuthorities(authorities);
        ResponseResult<Object> result = new ResponseResult<>();
        result.Success("登录成功",token);
        result.setData(response.getHeader("Authorization"));   // Add the token to the response
        response.getWriter().write(JSONUtil.toJsonStr(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
