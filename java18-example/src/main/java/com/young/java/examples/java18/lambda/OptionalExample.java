package com.young.java.examples.java18.lambda;

import java.util.Optional;

/**
 * @author shazam
 * @DATE 2018/3/17
 */
public class OptionalExample {

    public static void test() {
        String str = null;
        System.out.println(Optional.ofNullable(str).orElse("111"));
        System.out.println(Optional.ofNullable(str).orElseGet(String::new));
        System.out.println(Optional.ofNullable(str).isPresent());
        System.out.println(Optional.ofNullable(str).map(m->m.trim()).isPresent());
    }

    public static void test1(String name, Integer age) {
        User user = new User();
        Optional.ofNullable(name).ifPresent(user::setName);
        Optional.ofNullable(age).ifPresent(user::setAge);
        Optional.ofNullable(user.getName()).ifPresent(System.out::println);
        Optional.ofNullable(user.getAge()).ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        OptionalExample.test();
        OptionalExample.test1("yangyong",123);
    }
}
