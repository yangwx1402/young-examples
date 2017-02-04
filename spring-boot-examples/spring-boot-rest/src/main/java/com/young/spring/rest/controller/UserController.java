package com.young.spring.rest.controller;


import com.young.spring.rest.configuration.ProjectConfig;
import com.young.spring.rest.dao.UserDao;
import com.young.spring.rest.entity.RestResult;
import com.young.spring.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yangyong3 on 2017/2/3.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProjectConfig projectConfig;
    @Autowired
    private UserDao userDao;
    //可以使用redis
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(method = RequestMethod.PUT)
    public RestResult addUser(@RequestBody User user) {
        userDao.save(user);
        return new RestResult(1, projectConfig.getDesc());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RestResult<User> getUser(@PathVariable Integer id) {
        return new RestResult<User>(1, "success", userDao.findOne(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public RestResult updateUser(User user) {
        userDao.save(user);
        return new RestResult(1, "success");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RestResult deleteUser(@PathVariable Integer id) {
        userDao.delete(id);
        return new RestResult<User>(1, "success");
    }

    @RequestMapping(method = RequestMethod.GET)
    public RestResult<User> listUser() {
        return new RestResult<User>(1, "success", userDao.findAll());
    }
}
