package com.young.java.examples.map;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/8/14.
 * 功能同HashMap一样，只不过key值必须严格相等 key1==key2的情况下，才认为key相等。
 */
public class IdentityHashMapExample {

    private static class User{
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (age != user.age) return false;
            return name.equals(user.name);

        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age;
            return result;
        }
    }

    private Map<User,String> map = new IdentityHashMap<>();

    public void test(){
        User user1 = new User("yangyong",1);
        User user2 = new User("yangyong",1);
        System.out.println(user1 == user2);

        map.put(user1,"1");
        map.put(user2,"2");

        System.out.println(map);

        Map<User,String> hashmMp = new HashMap<>();
        hashmMp.put(user1,"1");
        hashmMp.put(user2,"2");
        System.out.println(hashmMp);
    }
    public static void main(String[] args){
        IdentityHashMapExample example = new IdentityHashMapExample();
        example.test();
    }
}
