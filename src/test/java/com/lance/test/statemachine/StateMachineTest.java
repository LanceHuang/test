package com.lance.test.statemachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * @author Lance
 * @since 2021/9/9
 */
public class StateMachineTest {

    @Test
    public void test() throws Exception {
        StateMachineBuilder.Builder<ObjectState, ObjectStateChange> builder = StateMachineBuilder.builder();

        // config
        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true) // 自动start
                .listener(new StateMachineListenerAdapter<ObjectState, ObjectStateChange>() { // 监听器，每个状态改变都会处理

                    @Override
                    public void stateChanged(State<ObjectState, ObjectStateChange> from, State<ObjectState, ObjectStateChange> to) {
                        System.out.println("State change to " + to.getId());
                    }
                });

        // states
        builder.configureStates()
                .withStates()
                .initial(ObjectState.S1) // 初始状态
                .end(ObjectState.S3) // 结束状态
                .states(EnumSet.allOf(ObjectState.class)); // 所有状态

        // transitions
        builder.configureTransitions()
                .withExternal().source(ObjectState.S1).target(ObjectState.S2).event(ObjectStateChange.S1S2)
                .and()
                .withExternal().source(ObjectState.S1).target(ObjectState.S3).event(ObjectStateChange.S1S3)
                .and()
                .withExternal().source(ObjectState.S2).target(ObjectState.S3).event(ObjectStateChange.S2S3);

        // test
        StateMachine<ObjectState, ObjectStateChange> stateMachine = builder.build();
        stateMachine.sendEvent(ObjectStateChange.S1S2); // 发送事件，触发状态转换
        Assertions.assertFalse(stateMachine.isComplete());
        stateMachine.sendEvent(ObjectStateChange.S2S3);
        Assertions.assertTrue(stateMachine.isComplete());
    }
}
