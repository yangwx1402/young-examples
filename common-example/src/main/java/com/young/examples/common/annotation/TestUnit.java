package com.young.examples.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2016/3/24.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestUnit {
    /**
     * 单元测试名称
     * @return
     */
    public String name();
}
