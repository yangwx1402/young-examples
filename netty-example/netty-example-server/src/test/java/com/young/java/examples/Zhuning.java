package com.young.java.examples;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2016/3/24.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Zhuning {
    public String name() default "pao";

    public String execute() default "";
}
