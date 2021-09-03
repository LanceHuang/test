package com.lance.test.statemachine.listener;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author Lance
 * @since 2021/9/3
 */
@WithStateMachine
public class OrderListener {

    @OnTransition(source = "NONE", target = "RUNNING")
    public void running() {
        System.out.println("Running order");
    }
}
