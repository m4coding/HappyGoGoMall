package com.m4coding.happygogo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.regex.Pattern;

/**
 * @author m4coding
 * <p>
 * Spring根配置
 */
@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages = {"com.m4coding.happygogo"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, value = RootConfig.WebPackage.class)
})
public class RootConfig {

//    /**
//     * 用于web包过滤，不让ComponentScan去扫描到
//     */
//    public static class WebPackage extends RegexPatternTypeFilter {
//
//        public WebPackage() {
//            super(null);
//        }
//
//        public WebPackage(Pattern pattern) {
//            super(Pattern.compile("com\\.m4coding\\.happygogo\\.web"));
//        }
//    }

    public static class WebPackage extends RegexPatternTypeFilter {
        public WebPackage() {
            super(Pattern.compile("com\\.m4coding\\.happygogo\\.web"));
        }
    }
}
