package com.lance.test.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Lance
 */
public abstract class MixApplicationEvent extends ApplicationEvent {

    public MixApplicationEvent(Object source) {
        super(source);
    }

    public abstract EventType getType();
}
