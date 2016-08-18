package com.young.java.examples.boot.app1.web;

import com.young.java.examples.boot.app1.service.App1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dell on 2016/8/18.
 */

/**
 * @RestController 因为我们例子是写一个web应用，因此写的这个注解，这个注解相当于同时添加@Controller和@ResponseBody注解。
 */
@RestController
public class App1Index {

    @Autowired
    private App1Service app1Service;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.PUT)
    public String add(@PathVariable int id) {
        return "add id =" + id;
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public String del(@PathVariable int id) {
        return "delete id = " + id;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, @RequestParam String name) {
        return "update id = " + id + ",name = " + name;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id) {
        return "get id = " + id;
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String getName(){
        return app1Service.getName();
    }

}
