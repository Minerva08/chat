package com.example.socketchat.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //프로필 이미지 url resource mapping
        registry.addResourceHandler("/anne/img/profile/**")
                .addResourceLocations("/Users/jaeryeongkim/Desktop/contents/img/profile");

        registry.addResourceHandler("/anne/js/**")
                .addResourceLocations("classpath:static/js/");

        registry.addResourceHandler("/anne/css/**")
                .addResourceLocations("classpath:static/css/");

    }
}
