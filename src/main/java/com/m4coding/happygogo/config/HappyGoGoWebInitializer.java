package com.m4coding.happygogo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author m4coding
 * <p>
 * Spring相关配置类
 */
public class HappyGoGoWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() { //配置ContextLoaderListener
        return new Class<?>[]{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() { //配置DispatcherServlet
        return new Class<?>[]{WebConfig.class};
    }

    protected String[] getServletMappings() { //将路径映射到DispatcherServlet
        return new String[]{"/"};
    }
}
