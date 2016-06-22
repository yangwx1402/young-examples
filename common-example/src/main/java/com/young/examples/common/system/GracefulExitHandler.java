package com.young.examples.common.system;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * Created by dell on 2016/6/21.
 */
public class GracefulExitHandler implements SignalHandler {
    @Override
    public void handle(Signal signal) {
        System.out.println("receive a signal name is - ["+signal.getName()+"],number is - ["+signal.getNumber()+"]");
    }
}
