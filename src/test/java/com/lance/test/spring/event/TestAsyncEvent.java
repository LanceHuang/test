package com.lance.test.spring.event;

/**
 * 异步事件
 *
 * @author Lance
 */
public class TestAsyncEvent extends MixApplicationEvent {

    public TestAsyncEvent(Object source) {
        super(source);
    }

    @Override
    public EventType getType() {
        return EventType.ASYNC;
    }
}
