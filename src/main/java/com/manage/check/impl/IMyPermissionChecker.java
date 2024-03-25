//package com.manage.check.impl;
//
//import com.manage.check.MyPermissionChecker;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//
//import java.util.Collection;
//
///**
// * @author lyq
// * @time 2024/3/24 0:55
// */
//
//@Component
//public class IMyPermissionChecker implements MyPermissionChecker {
//
//    /**
//     * 统一的权限校验方法
//     * 每次请求时，都传递一个参数，参数名字时perm，参数的值是权限标记，例如：perm=student:query
//     * 检查权限时，设定规则如下：
//     * 1、如果访问路径是/login，/loginFail 不检查权限，直接放行
//     * 2、其他的访问路径地址，则获取请求参数，参数名perm，值是此请求地址必须拥有的权限标记
//     * @param request 请求
//     * @param authentication 认证对象 Security框架管理的认证用户主体对象
//     *                       在内存中，具体类型是UsernamePasswordAuthenticationToken
//     * @return 是否有权限
//     */
//    @Override
//    public boolean check(HttpServletRequest request, Authentication authentication) {
////        获取本次请求的路径地址
//        String path = request.getServletPath();
////        如果访问路径是/login，/loginFail 不检查权限，直接放行
//        if ("/login".equals(path) || "/loginFail".equals(path)){
//            return true;
//        }
////        获取请求参数
//        String perm = request.getParameter("perm");
//        perm = (perm != null && !perm.trim().isEmpty()) ? perm.trim() : "none";
////        获取权限前，需要先判断用户是否已经登录
//        if (authentication == null){
//            return false;
//        }else {
////            获取当前已经认证登录的用户的权限
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
////            判断权限集合中是否存在请求需要的权限
//            if(authorities.contains(new SimpleGrantedAuthority(perm))){
////                有权限
//                return true;
//            }else {
//                return false;
//            }
//        }
//    }
//}
