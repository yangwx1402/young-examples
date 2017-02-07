package com.young.spring.rest.statemachine;

import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

/**
 * Created by yangyong3 on 2017/2/7.
 */
public class TradeStateMachineListener extends StateMachineListenerAdapter<States, Events> {

    @Override
    public void transition(Transition<States, Events> transition) {
        System.out.println("source = " + transition.getSource() + ",target = " + transition.getTarget());
        if (transition.getSource() == null && transition.getTarget().getId() == States.UNPAID) {
            System.out.println("您还没有支付，请先支付");
        } else if (transition.getSource().getId() == States.UNPAID && transition.getTarget().getId() == States.WAIT_PACKAGE) {
            System.out.println("您以支付，请等待收货");
        } else if (transition.getSource().getId() == States.WAIT_PACKAGE && transition.getTarget().getId() == States.FINISHED) {
            System.out.println("您已收货,交易完成");
        }
    }
}
