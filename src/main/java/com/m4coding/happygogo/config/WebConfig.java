package com.m4coding.happygogo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

/**
 * @author m4coding
 * <p>
 * spring web配置
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.m4coding.happygogo.web.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

    /*@Autowired
    private PropertiesConfig propertyConfig;

    @Bean
    public ViewResolver viewResolver() { //配置JSP视图解析器
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(propertyConfig.getWebViewPrefix());
        resolver.setSuffix(propertyConfig.getWebViewPrefix());
        return resolver;
    }

    *//**
     * 设置统一错误处理要跳转的视图
     *
     * @return
     *//*
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.getProperty("java.lang.Exception", "error");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        return simpleMappingExceptionResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable(); //将静态资源请求转发到Servlet容器中默认的Servlet上，而不是使用DispatcherServlet本身来处理此类请求
    }*/

    @Bean
    public ViewResolver viewResolver() {
        System.out.println("#####viewResolver");
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        super.addResourceHandlers(registry);
    }
}
