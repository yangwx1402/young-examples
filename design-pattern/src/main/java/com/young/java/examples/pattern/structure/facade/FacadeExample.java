package com.young.java.examples.pattern.structure.facade;

/**
 * Created by yangyong3 on 2017/6/29.
 * 门面模式(外观模式)，解决类与类之间的耦合关系，采用组合实现
 * 这样做的话，调用方只用关心Computer类，我们在Computer内部封装
 * 组合对象的调用，这样外部调用者就不用关心内部的关系，
 */
public class FacadeExample {
    public static void main(String[] args) {
        Computer computer = new Computer(new Cpu(), new Disk(), new Memory());
        computer.startup();
        computer.shutdown();
    }
}
