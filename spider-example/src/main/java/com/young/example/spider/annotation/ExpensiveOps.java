package com.young.example.spider.annotation;

import java.lang.annotation.*;

/**
 * Created by yangyong3 on 2017/2/22.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface ExpensiveOps {
}
