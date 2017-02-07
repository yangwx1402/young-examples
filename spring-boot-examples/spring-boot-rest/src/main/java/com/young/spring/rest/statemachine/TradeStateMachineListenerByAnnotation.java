package com.young.spring.rest.statemachine;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * Created by yangyong3 on 2017/2/7.
 */
@WithStateMachine
public class TradeStateMachineListenerByAnnotation {

    @OnTransition(target = "UNPAID")
    public void create(){
        System.out.println("您还为支付");
    }
    @OnTransition(source = "UNPAID",target = "WAIT_PACKAGE")
    public void receive(){
        System.out.println("您已支付,等待收货");
    }
    @OnTransition(source = "WAIT_PACKAGE",target = "FINISHED")
    public void finish(){
        System.out.println("您已收货，交易结束");
    }
}
