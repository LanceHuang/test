package com.lance.test.statemachine.config;

import com.lance.test.statemachine.ObjectState;
import com.lance.test.statemachine.ObjectStateChange;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * @author Lance
 * @since 2021/9/9
 */
@EnableStateMachine(name = "myStateMachine")
public class MyStateMachineConfiguration extends StateMachineConfigurerAdapter<ObjectState, ObjectStateChange> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<ObjectState, ObjectStateChange> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true) // 自动start
                .listener(new StateMachineListenerAdapter<ObjectState, ObjectStateChange>() { // 监听器，每个状态改变都会处理

                    @Override
                    public void stateChanged(State<ObjectState, ObjectStateChange> from, State<ObjectState, ObjectStateChange> to) {
                        System.out.println("State change to " + to.getId());
                    }
                });
    }

    @Override
    public void configure(StateMachineStateConfigurer<ObjectState, ObjectStateChange> states) throws Exception {
        states.withStates()
                .initial(ObjectState.S1) // 初始状态
                .end(ObjectState.S3) // 结束状态
                .states(EnumSet.allOf(ObjectState.class)); // 所有状态
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ObjectState, ObjectStateChange> transitions) throws Exception {
        transitions.withExternal().source(ObjectState.S1).target(ObjectState.S2).event(ObjectStateChange.S1S2)
                .and()
                .withExternal().source(ObjectState.S1).target(ObjectState.S3).event(ObjectStateChange.S1S3)
                .and()
                .withExternal().source(ObjectState.S2).target(ObjectState.S3).event(ObjectStateChange.S2S3);
    }
}
