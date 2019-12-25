package com.m4coding.mallforeground.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan({"com.m4coding.mallmbg.mbg.mapper", "com.m4coding.mallforeground.dao"})
public class MyBatisConfig {
}
