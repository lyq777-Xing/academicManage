package com.manage.security;

import cn.hutool.core.util.StrUtil;
import com.manage.entity.User;
import com.manage.service.UserService;
import com.manage.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author lyq
 * @time 2024/3/25 20:42
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private IMyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header = request.getHeader(jwtUtils.getHeader());
        if(StrUtil.isBlankOrUndefined(header)){
            chain.doFilter(request,response);
            return;
        }

        Claims claimByToken = jwtUtils.getClaimByToken(header);
        if(claimByToken == null){
            throw new JwtException("token 异常");
        }

        if(jwtUtils.isTokenExpired(claimByToken)){
            throw new JwtException("token已过期");
        }

        String username = claimByToken.getSubject();

//       获取用户id
        User byUsername = userService.findByUsername(username);
        Integer id = byUsername.getId();

//        获取用户的权限信息
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, userDetailsService.getUserAuthority(id));
        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request,response);
    }
}
