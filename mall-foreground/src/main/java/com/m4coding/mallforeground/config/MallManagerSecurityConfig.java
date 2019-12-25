package com.m4coding.mallforeground.config;

import com.m4coding.mallforeground.service.impl.UmsUserServiceImpl;
import com.m4coding.mallsecurity.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 前台管理安全性配置
 */
@Configuration
@EnableWebSecurity
public class MallManagerSecurityConfig extends SecurityConfig {

    // Spring会自动寻找实现接口的类注入,会找到我们的 UserDetailsServiceImpl类
    @Autowired
    private UmsUserServiceImpl userDetailsService;

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}
