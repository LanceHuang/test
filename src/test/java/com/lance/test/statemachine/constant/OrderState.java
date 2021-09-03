package com.lance.test.statemachine.constant;

/**
 * 订单状态
 *
 * @author Lance
 * @since 2021/9/3
 */
public enum OrderState {

    /** 初始状态 */
    NONE,
    /** 进行中 */
    RUNNING,
    /** 等待中 */
    WAITING,
    /** 阻塞中 */
    BLOCKING,
    /** 已完成 */
    FINISHED,
}
