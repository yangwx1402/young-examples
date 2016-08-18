package com.young.java.examples.boot.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2016/8/18.
 */
@RestController
@EnableAutoConfiguration
public class FirstAction {
    @RequestMapping("/first/{id}/{name}")
    public String first(@PathVariable int id,@PathVariable String name){
        return name+" you are first,you're id is "+id;
    }
}
