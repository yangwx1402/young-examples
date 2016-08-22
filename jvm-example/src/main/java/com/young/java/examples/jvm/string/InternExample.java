package com.young.java.examples.jvm.string;

/**
 * Created by young.yang on 2016/8/20.
 */
public class InternExample {
    public void test1(){
        //会在常量池中缓存一个hello,将常量池中hello的引用返回给s0
        String s0 = "hello";
        //因为常量池中已经有了hello,那么只是创建了一个常量池中hello的引用
        String s1 = new String("hello");
        String s2 = new String("hello");
        //false 因为s0和s1均是引用,所以为false
        System.out.println(s0==s1);
        //同上
        System.out.println(s1==s2);
        //intern方法如果常量池中有hello,那么就返回hello的引用,也就是s0,所以s0==s1 返回true
        System.out.println(s0==s1.intern());
        System.out.println(s0.hashCode()==s1.hashCode());
        System.out.println(s1.intern()==s2.intern());
    }
    public static void main(String[] args){
        InternExample example = new InternExample();
        example.test1();
    }
}
