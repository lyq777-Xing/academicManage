package com.manage.security;

import cn.hutool.json.JSONUtil;
import com.manage.common.ResponseResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;


/**
 * @author lyq
 * @time 2024/3/25 18:04
 * 未登录处理类
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseResult<Object> result = new ResponseResult<>();
        result.Error("未登录", 401);
        result.setData(null);
        response.getWriter().write(JSONUtil.toJsonStr(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}