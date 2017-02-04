package com.young.spring.rest.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by yangyong3 on 2017/2/3.
 */
@Component
public class ProjectConfig {
    @Value("${com.young.spring.desc}")
    private String desc;
    @Value("${com.young.spring.title}")
    private String title;
    @Value("${com.young.spring.author}")
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
