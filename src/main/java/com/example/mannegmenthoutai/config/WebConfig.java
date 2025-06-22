package com.example.mannegmenthoutai.config;

import com.example.mannegmenthoutai.interceptor.ResponseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ResponseInterceptor responseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/users/login", "/api/users/register");
    }
} 