package com.young.java.examples.pattern.constructor.prototype;

/**
 * Created by yangyong3 on 2017/6/30.
 * Prototype(原型模型)主要用来对象拷贝场景中，java中的clone分为浅拷贝(只拷贝了引用)和深拷贝(值拷贝)
 */
public class PrototypeExample {
    public static void qiancopy() throws CloneNotSupportedException {
        User user = new User();
        user.setName("yangyong");
        user.setAge(20);
        User child = new User();
        child.setName("111");
        child.setAge(1);
        user.setChild(child);
        User user2 = (User) user.clone();
        //这里user是不等于user2的，因为copy的时候创建了一个新的User对象
        System.out.println(user == user2);
        //这里child是相等的，因为默认的java Clone是钱拷贝，user和user2中的child是同一个对象
        System.out.println(user.getChild() == user.getChild());
        //下面两个打印的地址是一样的，说明内部只是将引用copy了。
        System.out.println(user.getChild());
        System.out.println(user2.getChild());
    }
    public static void main(String[] args) throws CloneNotSupportedException {
      PrototypeExample.qiancopy();
    }
}
