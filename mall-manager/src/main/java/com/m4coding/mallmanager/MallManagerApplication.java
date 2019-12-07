package com.m4coding.mallmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MallManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallManagerApplication.class, args);
    }

}
