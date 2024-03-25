package com.manage.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author lyq
 * @time 2024/3/25 18:54
 */
@Data
public class AuthenticationToken {
    private String token;
    private Collection<? extends GrantedAuthority> authorities;
}
