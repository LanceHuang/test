package com.lance.test.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 混合事件
 *
 * @author Lance
 */
public abstract class MixApplicationEvent extends ApplicationEvent {

    public MixApplicationEvent(Object source) {
        super(source);
    }

    /**
     * 判断是否同步事件
     */
    public abstract boolean isSync();
}
