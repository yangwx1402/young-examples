package com.young.java.examples.pattern.structure.facade;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class Computer {

    private Cpu cpu;

    private Disk disk;

    private Memory memory;

    public Computer(Cpu cpu,Disk disk,Memory memory){
        this.cpu = cpu;
        this.disk = disk;
        this.memory = memory;
    }

    public void startup(){
        cpu.startup();
        disk.startup();
        memory.startup();
        System.out.println("computer start up");
    }

    public void shutdown(){
        cpu.shutdown();
        disk.shutdown();
        memory.shutdown();
        System.out.println("computer shut down");
    }
}
