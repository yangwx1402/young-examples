package com.young.example.rest.framework.build.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(value = "com.young", excludeFilters = {@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@EnableAspectJAutoProxy
public class BuildConfig {
//    @Bean
//    public ObjectMapper getJsonMessageConverter() {
//        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
//        // Add supported media type returned by BI API.
//        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
//        supportedMediaTypes.add(new MediaType("text", "plain"));
//        supportedMediaTypes.add(new MediaType("application", "json"));
//        jsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
//        return jsonMessageConverter.getObjectMapper();
//    }
}
