package com.young.java.examples.pattern.behaviour.command;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public class MyCommand implements Command {

    private CommandReceiver receiver;

    public MyCommand(CommandReceiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
          receiver.action();
    }
}
