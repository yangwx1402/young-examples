package com.young.java.examples.pattern.behaviour.command;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public class Commander {
    private Command command;

    public Commander(Command command){
        this.command = command;
    }


    public void sendCommand(){
        command.execute();
    }
}
