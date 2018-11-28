package com.lance.test.event;

/**
 * A question comes to us. How to handle different event in DDD(Domain Driver Design)?
 * Just as swing, you can register some ActionListener, but must implements interface Observable every where.
 * A scheme is as follow:
 * ------------------------------------------------------------------------
 * Code B -------------- Event B ----------------->> |   |
 * Code C -------------- Event A ----------------->> |   |
 * Code A -------------- Event A ----------------->> |   |
 * Code D -------------- Event D ----------------->> |   |
 * ------------------------------------------------- |   |
 * ------------------------------------------------- |   |
 * ------------------------------------------------- |   |  (Event bus)
 * ------------------------------------------------- |   |
 * ------------------------------------------------- |   |
 * Handler A <<-------------- Event A -------------- |   |
 * Handler B <<-------------- Event B -------------- |   |
 * Handler D <<-------------- Event D -------------- |   |
 * ------------------------------------------------------------------------
 * <p>
 * Handlers don't listen to event bus, but just register to it, so Handler.doHandler() can be synchronous and asynchronous.
 *
 * @author Lance
 * @since 2018-10-27 16:05:50
 */
public interface IEventManager {

    /**
     * Register event invoker
     */
    void registerEventInvoker(Class<? extends IEvent> eventClass, IEventInvoker invoker);

    /**
     * Synchronize submit event
     */
    void syncSubmit(IEvent event);

    /**
     * Asynchronous submit event
     */
    void asyncSubmit(IEvent event);
}
