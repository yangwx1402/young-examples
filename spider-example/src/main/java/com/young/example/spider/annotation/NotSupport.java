package com.young.example.spider.annotation;

import java.lang.annotation.*;

/**
 * Created by yangyong3 on 2017/2/22.
 */
@Retention(RetentionPolicy.CLASS)
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface NotSupport {
}
