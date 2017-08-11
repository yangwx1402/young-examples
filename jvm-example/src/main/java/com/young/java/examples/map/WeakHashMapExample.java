package com.young.java.examples.map;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by yangyong3 on 2017/8/8.
 */
public class WeakHashMapExample {

    public static class User {
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
            System.out.println(this.name+","+this.getAge()+" destoryed");
        }
    }

    private Map<User, Integer> cache = new WeakHashMap<User, Integer>();

    public void test() throws InterruptedException {
        User[] app = new User[10];
        for(int i=0;i<30;i++){
            User user = new User("name_"+i,i);
            if(i%3==0){
                app[i/3] = user;
            }
            cache.put(user,i);
        }
        app[0] = null;
        System.gc();

        Thread.sleep(10000);
        System.out.println(app.length);
        System.out.println(cache.size());
        System.out.println(cache);
    }
    public static void main(String[] args) throws InterruptedException {
        WeakHashMapExample example = new WeakHashMapExample();
        example.test();
    }
}
