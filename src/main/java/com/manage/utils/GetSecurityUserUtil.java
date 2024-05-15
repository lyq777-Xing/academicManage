package com.manage.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @time 2024/3/25 21:41
 * 获取当前登录用户
 */

@Component
public class GetSecurityUserUtil {
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            // 这里可以根据需要从authentication对象中提取所需的用户信息
            return authentication.getName();
        } else {
            return null;
        }
    }
}
