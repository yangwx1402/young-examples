package com.young.examples.common.system;

import sun.misc.Signal;

/**
 * Created by dell on 2016/6/21.
 */
public class GracefulExit {

    public String getOsSingalType(){
        return System.getProperties().getProperty("os.name").toLowerCase().startsWith("win")?"INT":"USR2";
    }

    public void exit(){
        //第一步获取signal信号
        Signal signal = new Signal(getOsSingalType());
        //
        Signal.handle(signal,new GracefulExitHandler());
        System.exit(12);
    }

    public static void main(String[] args) {
        GracefulExit exit = new GracefulExit();
        exit.exit();
    }
}
