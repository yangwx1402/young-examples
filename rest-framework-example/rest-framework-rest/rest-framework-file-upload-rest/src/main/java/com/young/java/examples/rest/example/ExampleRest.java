package com.young.java.examples.rest.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2016/5/17.
 */
@Controller
@RequestMapping("/test")
@ResponseBody
public class ExampleRest {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public UserEntity test() {
        UserEntity user = new UserEntity();
        user.setAge(30);
        user.setName("yy");
        return user;
    }
}
