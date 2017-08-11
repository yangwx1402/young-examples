package com.young.java.examples.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/8/11.
 */
public class SoftReferenceExample {

    private static class User{
        private String name;

        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println(name+":"+age+" destory");
        }
    }

    public static void test1() throws InterruptedException {
        int length = 20000;
        User[] users = new User[length];
        Map<User,User> map = new HashMap<User,User>();
        for(int i=0;i<length;i++){
            //软引用，当内存空间够用的时候，软引用不会被回收，但是当内存空间不够的时候会自动进行回收
            SoftReference<User> sf = new SoftReference<User>(new User("yangyong_"+i,i));
            users[i] = sf.get();
            Thread.sleep(1);
        }
    }

    public static void test2() throws InterruptedException {
        int length = 20000;
        User[] users = new User[length];
        ReferenceQueue<User> queue = new ReferenceQueue<>();
        for(int i=0;i<length;i++){
            //软引用，当内存空间够用的时候，软引用不会被回收，但是当内存空间不够的时候会自动进行回收
            SoftReference<User> sf = new SoftReference<User>(new User("yangyong_"+i,i),queue);
            users[i] = sf.get();
            Thread.sleep(2);
        }
        System.out.println(queue.poll());
    }

    public static void main(String[] args) throws InterruptedException {
         test1();
        test2();
    }
}
