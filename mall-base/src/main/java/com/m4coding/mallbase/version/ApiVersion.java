package com.m4coding.mallbase.version;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * api版本控制注解
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {

    /**
     * version
     *
     * @return
     */
    int value() default 1;
}
