package com.m4coding.mallforeground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MallForegroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallForegroundApplication.class, args);
    }

}
