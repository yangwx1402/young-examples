package com.young.java.examples.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by young.yang on 2016/11/10.
 */
@Configuration
@ComponentScan("com.young.java.examples.spring.aop")
//开启aspectJ自动代理,这样可以自动加载切面
@EnableAspectJAutoProxy
public class LogConfig {

}
