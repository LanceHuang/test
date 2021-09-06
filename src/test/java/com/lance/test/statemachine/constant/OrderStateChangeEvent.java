package com.lance.test.statemachine.constant;

/**
 * 订单状态更新事件
 *
 * @author Lance
 * @since 2021/9/3
 */
public enum OrderStateChangeEvent {

    RUN,
    STOP_RUN,
    WAIT,
    BLOCK,
    ;
}
