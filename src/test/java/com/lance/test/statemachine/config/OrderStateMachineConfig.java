package com.lance.test.statemachine.config;

import com.lance.test.statemachine.constant.OrderState;
import com.lance.test.statemachine.constant.OrderStateChangeEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author Lance
 * @since 2021/9/3
 */
@Configuration
@EnableStateMachine
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderStateChangeEvent> {

    /**
     * 配置状态
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderStateChangeEvent> states) throws Exception {
        // 初始状态和所有状态
        states.withStates().initial(OrderState.NONE).states(EnumSet.allOf(OrderState.class));
    }

    /**
     * 配置转换关系
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderStateChangeEvent> transitions) throws Exception {
        transitions
                .withExternal().source(OrderState.NONE).target(OrderState.RUNNING).event(OrderStateChangeEvent.RUN)
                .and()
                .withExternal().source(OrderState.NONE).target(OrderState.WAITING).event(OrderStateChangeEvent.WAIT)
                .and()
                .withExternal().source(OrderState.NONE).target(OrderState.BLOCKING).event(OrderStateChangeEvent.BLOCK);
    }
}
