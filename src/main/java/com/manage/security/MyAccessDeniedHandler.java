package com.manage.security;

import com.manage.common.Meta;
import com.manage.common.ResponseResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import cn.hutool.json.JSONUtil;
import java.io.IOException;

/**
 * @author lyq
 * @time 2024/3/24 9:45
 * 自定义403处理器
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 处理403异常
     * @param request 请求
     * @param response 响应
     * @param accessDeniedException 403异常
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        通过响应输出流，向客户端输出一个友好结果
        response.setContentType("application/json;charset=utf-8");
        ResponseResult<Object> ResponseResult = new ResponseResult<>();
        ResponseResult.setMeta(new Meta("权限不足", 403));
        ResponseResult.setData(null);
        response.getWriter().write(JSONUtil.toJsonStr(ResponseResult));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
