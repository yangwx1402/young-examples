package com.young.java.examples.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by young.yang on 2016/11/12.
 */
@Configuration
@ComponentScan("com.young.java.examples")
@ImportResource("classpath:log-spring.xml")
@EnableAspectJAutoProxy
public class BootConfig {
}
