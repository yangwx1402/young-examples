package com.young.java.examples.pattern.constructor.singleton.two;

/**
 * Created by yangyong3 on 2017/6/29.
 * 采用枚举实现的单例模式，目前来看该办法是最好的
 */
public enum Singleton {
    instance;

    public String toString(){
        return "hh";
    }

    public static void main(String[] args){
       System.out.println(Singleton.instance.toString());
    }
}
