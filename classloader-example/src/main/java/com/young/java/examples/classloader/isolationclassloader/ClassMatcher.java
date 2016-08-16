package com.young.java.examples.classloader.isolationclassloader;

/**
 * @author young.yang
 */
public interface ClassMatcher {
    /**
     * @return 用来匹配类
     */
    boolean match(String className, String pattern);

    /**
     * 用来验证正则表达式
     * @param pattern
     * @return
     */
    boolean validate(String pattern);
}
