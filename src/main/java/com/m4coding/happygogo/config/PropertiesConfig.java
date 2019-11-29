package com.m4coding.happygogo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author m4coding
 *
 * 外部定义属性配置
 */
@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver}")
    private String driver;

    @Value("${spring.datasource.user}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.web.view.prefix}")
    private String webViewPrefix;

    @Value("${spring.web.view.suffix}")
    private String webViewSuffix;

    @Value("${spring.web.static.handler}")
    private String webStaticHandler;

    @Value("${spring.web.static.resource}")
    private String webStaticResource;

    @Value("${spring.web.static.cache.period}")
    private Integer webStaticCachedPeriod;

    @Value("${mybatis.type.alias.package}")
    private String mybatisTypeAliasPackage;

    @Value("${mybatis.mapper.locations}")
    private String mapperLocations;

    @Value("${mybatis.config.locations}")
    private String mybatisConfigLocations;

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getWebViewPrefix() {
        return webViewPrefix;
    }

    public String getWebViewSuffix() {
        return webViewSuffix;
    }

    public String getWebStaticHandler() {
        return webStaticHandler;
    }

    public String getWebStaticResource() {
        return webStaticResource;
    }

    public Integer getWebStaticCachedPeriod() {
        return webStaticCachedPeriod;
    }

    public String getMybatisTypeAliasPackage() {
        return mybatisTypeAliasPackage;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public String getMybatisConfigLocations() {
        return mybatisConfigLocations;
    }

    //为了使用占位符，我们必须要配置一个PropertyPlaceholderConfigurer bean或PropertySourcesPlaceholderConfigurer bean。
    // 从Spring 3.1开始，推荐使用PropertySourcesPlaceholderConfigurer，因为它能够基于Spring Environment及其属性源来解析占位符。
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
