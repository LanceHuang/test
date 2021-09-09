package com.lance.test.statemachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

/**
 * @author Lance
 * @since 2021/9/9
 */
@SpringBootTest(classes = SpringStateMachineTest.Config.class)
public class SpringStateMachineTest {

    @Autowired
    @Qualifier("myStateMachine")
    private StateMachine<ObjectState, ObjectStateChange> stateMachine;

    @Test
    public void testEnableStateMachine() {
        stateMachine.sendEvent(ObjectStateChange.S1S2);
        Assertions.assertFalse(stateMachine.isComplete());
        stateMachine.sendEvent(ObjectStateChange.S2S3);
        Assertions.assertTrue(stateMachine.isComplete());
    }

    @SpringBootApplication
    public static class Config {
    }
}
