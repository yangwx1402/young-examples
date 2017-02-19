package com.young.spring.cloud.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangyong3 on 2017/2/9.
 */
@RestController
@RequestMapping
public class ComputerService {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(@RequestParam Integer first, @RequestParam Integer second) {
        System.out.println("i am execute");
        return first + second;
    }
}
