package com.m4coding.mallmbg.mbg;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * MyBatis Generator类型转换自定义
 */
public class MyBatisJavaTypeResolver extends JavaTypeResolverDefaultImpl {
    public MyBatisJavaTypeResolver() {
        super();
        //使TINYINT转换为Integer，默认是转为byte的
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT",
                new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
