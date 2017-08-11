package com.young.java.examples.reference;

import java.lang.ref.WeakReference;

/**
 * Created by yangyong3 on 2017/8/11.
 */
public class WeakReferenceExample {
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

    public static void main(String[] args) throws InterruptedException {
        int length = 1000;
        User[] users = new User[length];
        for(int i=0;i<length;i++){
            /**
             * 弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
             弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
             */
            WeakReference<User> sf = new WeakReference<User>(new User("yangyong_"+i,i));
            users[i] = sf.get();
            Thread.sleep(10);
        }
    }
}
