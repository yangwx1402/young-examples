package com.young.spring.rest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by yangyong3 on 2017/2/4.
 */
@Configuration
/**
 * 用来扫描Reposity
 */
@EnableJpaRepositories("com.young.spring.rest.dao")
/**
 * 用来扫描Entity
 */
@EntityScan("com.young.spring.rest.entity")
public class JpaConfig {
}
