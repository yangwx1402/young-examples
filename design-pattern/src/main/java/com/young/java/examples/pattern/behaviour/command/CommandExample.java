package com.young.java.examples.pattern.behaviour.command;

/**
 * Created by yangyong3 on 2017/7/3.
 * 命令模式，有三种角色 命令发出者、命令执行者、命令本身
 * 这样解耦了命令发出者和命令执行者，命令发出者发出命令以后不需要关心命令执行者，
 * 只需要知道命令执行的结果即可。而命令和命令执行者之间是通过组合实现的。
 */
public class CommandExample {
    public static void main(String[] args) {
        //命令执行者
        CommandReceiver receiver = new CommandReceiver();
        //命令
        Command command = new MyCommand(receiver);
        //命令发出者
        Commander commander = new Commander(command);
        commander.sendCommand();
    }
}
