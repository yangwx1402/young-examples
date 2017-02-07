package com.young.spring.rest.boot;

import com.young.spring.rest.statemachine.Events;
import com.young.spring.rest.statemachine.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.statemachine.StateMachine;

/**
 * Created by yangyong3 on 2017/2/7.
 */
@SpringBootApplication
@ComponentScan("com.young.spring.rest.statemachine")
public class StateMachineBootStrap implements CommandLineRunner{

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... strings) throws Exception {
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);
    }

    public static void main(String[] args) {
         SpringApplication.run(StateMachineBootStrap.class,args);
    }
}
