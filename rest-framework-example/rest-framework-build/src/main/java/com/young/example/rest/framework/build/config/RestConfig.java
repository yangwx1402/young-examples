package com.young.example.rest.framework.build.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by dell on 2016/4/5.
 */
@Configuration
@ComponentScan("com.young.java.examples.rest")
@EnableWebMvc
public class RestConfig extends WebMvcConfigurerAdapter {

    @Bean
    public PageableHandlerMethodArgumentResolver pageableHandlerResolver() {
        return new PageableHandlerMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(pageableHandlerResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger/**").addResourceLocations("/swagger/");
    }

    @Bean
    public MultipartResolver multipartResolver(){
        MultipartResolver resolver = new CommonsMultipartResolver();
        return resolver;
    }
}
