package com.young.spring.rest.statemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;

import java.util.EnumSet;

/**
 * Created by yangyong3 on 2017/2/7.
 * 创建一个状态机的步骤
 * 1.定义状态和事件枚举
 * 2.为状态机定义使用的所有状态以及初始状态
 * 3.为状态机定义状态的迁移动作
 * 4.为状态机指定监听处理器
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    /**
     * 这个方法主要是定义初始化以及状态集合
     *
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> config) throws Exception {
        config.withStates().initial(States.UNPAID).states(EnumSet.allOf(States.class));
    }

    /**
     * 这个方法主要是定义状态的转移，也就是说收到什么事件的时候，状态从S1--->S2，这里要指定所有的转移规则
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions.withExternal().source(States.UNPAID).target(States.WAIT_PACKAGE).event(Events.PAY).and().withExternal().source(States.WAIT_PACKAGE).target(States.FINISHED).event(Events.RECEIVE);
    }

    /**
     * 这个方法是为状态机定义一个监听器来监听事件的发生
     * 如果采用annotation方式设置listener的话,就不需要实现这个方法了
     * @param config
     * @throws Exception
     */
//    @Override
//    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
//        config.withConfiguration().listener(listener());
//    }

    /**
     * 定义一个状态监听器，用来监听事件的处理,事件监听器还可以通过Annotation的方式实现。
     * @return
     */
//    @Bean
//    public StateMachineListener<States, Events> listener() {
//        return new TradeStateMachineListener();
//    }
}
