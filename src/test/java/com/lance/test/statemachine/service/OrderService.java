package com.lance.test.statemachine.service;

import com.lance.test.statemachine.constant.OrderState;
import com.lance.test.statemachine.model.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 * @since 2021/9/3
 */
@Service
public class OrderService implements IOrderService {

    private Map<Long, Order> orderMap = new HashMap<>();

    @Override
    public void createOrder(long id) {
        Order order = new Order();
        order.setId(id);
        order.setState(OrderState.NONE);
        orderMap.putIfAbsent(id, order);

        System.out.println("Create order: " + id);
    }

    @Override
    public void runOrder(long id) {
        Order order = orderMap.get(id);
        order.setState(OrderState.RUNNING);

        System.out.println("Run order: " + id);
    }

    @Override
    public void waitOrder(long id) {
        Order order = orderMap.get(id);
        order.setState(OrderState.WAITING);

        System.out.println("Wait order: " + id);
    }

    @Override
    public void blockOrder(long id) {
        Order order = orderMap.get(id);
        order.setState(OrderState.BLOCKING);

        System.out.println("Block order: " + id);
    }
}
