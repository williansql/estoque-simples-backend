package com.foffaps.estoquesimples.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // or any other origins you want to allow
                .allowedMethods("*") // or any other HTTP methods you want to allow
                .allowedHeaders("*"); // or specify which headers you want to allow
    }
}