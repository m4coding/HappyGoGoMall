package com.m4coding.mallforeground.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis配置类
 *
 * Configuration指定MyBatisConfig为Spring的一个配置
 * MapperScan配置查找Mapper接口路径
 */
@Configuration
@MapperScan("com.m4coding.mallforegound.mbg.mapper")
public class MyBatisConfig {
}
