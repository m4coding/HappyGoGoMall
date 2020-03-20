package com.m4coding.mallforeground.config;

import com.m4coding.mallforeground.filter.SwaggerUIFilter;
import com.m4coding.mallforeground.service.impl.UmsUserServiceImpl;
import com.m4coding.mallsecurity.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public FilterRegistrationBean registrationSwaggerUiFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(this.getSwaggerUiFilter());
        //针对swagger相关url进行过滤处理
        registration.addUrlPatterns("/swagger-ui.html");
        registration.addUrlPatterns("/swagger-resources");
        registration.addUrlPatterns("/swagger-resources/configuration/ui");
        registration.addUrlPatterns("/v2/api-docs");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public SwaggerUIFilter getSwaggerUiFilter(){
        return new SwaggerUIFilter();
    }
}
