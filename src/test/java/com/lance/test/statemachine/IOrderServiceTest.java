package com.lance.test.statemachine;

import com.lance.test.statemachine.constant.OrderState;
import com.lance.test.statemachine.constant.OrderStateChangeEvent;
import com.lance.test.statemachine.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

/**
 * @author Lance
 * @since 2021/9/3
 */
@SpringBootTest(classes = IOrderServiceTest.Config.class)
public class IOrderServiceTest {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private StateMachine<OrderState, OrderStateChangeEvent> stateMachine;

    @Test
    public void test() {
//        orderService.createOrder(System.currentTimeMillis());
        stateMachine.start();
        stateMachine.sendEvent(OrderStateChangeEvent.RUN);
        stateMachine.sendEvent(OrderStateChangeEvent.STOP_RUN);
    }

    @SpringBootApplication
    public static class Config {
    }
}