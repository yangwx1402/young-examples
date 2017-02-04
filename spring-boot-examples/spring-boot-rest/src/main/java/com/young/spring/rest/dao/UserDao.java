package com.young.spring.rest.dao;

import com.young.spring.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yangyong3 on 2017/2/4.
 * Dao配置
 */
public interface UserDao extends JpaRepository<User,Integer> {
}
