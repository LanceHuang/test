package com.lance.test.statemachine.service;

/**
 * 订单服务
 *
 * @author Lance
 * @since 2021/9/3
 */
public interface IOrderService {

    void createOrder(long id);

    void runOrder(long id);

    void waitOrder(long id);

    void blockOrder(long id);
}
