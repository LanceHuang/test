package com.lance.test.statemachine.model;

import com.lance.test.statemachine.constant.OrderState;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单
 *
 * @author Lance
 * @since 2021/9/3
 */
@Getter
@Setter
public class Order {

    private long id;

    private OrderState state;
}
