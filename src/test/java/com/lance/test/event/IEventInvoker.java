package com.lance.test.event;

/**
 * @author Lance
 * @since 2018-10-27 16:05:50
 */
public interface IEventInvoker {

    void invoke(IEvent event);
}
