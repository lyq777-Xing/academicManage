package com.manage.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lyq
 * @time 2024/1/26 13:26
 */
public class MyPasswordEncoder implements PasswordEncoder {

    /**
     * 对密码进行加密
     * @param rawPassword 原始密码
     * @return 加密后的密码
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    /**
     * 匹配密码是否正确
     * @param rawPassword 原始密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

    /**
     * 是否需要强化密码解析策略
     * @param encodedPassword 加密后的密码
     * @return 是否需要强化
     */
    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }
}
