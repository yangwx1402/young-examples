package com.young.spring.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangyong3 on 2017/2/3.
 * 测试下异常情况下
 */
@RestController
@RequestMapping("/test")
public class ErrorController {
    @RequestMapping(method = RequestMethod.GET)
    public void test() throws Exception {
        throw new Exception("测试下错误");
    }
}
