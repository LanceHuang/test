package com.lance.test.event;


import java.util.List;
import java.util.Map;

/**
 * @author Lance
 * @since 2018-10-27 16:05:50
 */
public class EventManager implements IEventManager {

    private Map<Class<? extends IEvent>, List<IEventInvoker>> invokerMap;

    @Override
    public void registerEventInvoker(Class<? extends IEvent> eventClz, IEventInvoker invoker) {
        if (!invokerMap.containsKey(eventClz)) {
//            invokerMap.put(eventClz, new )
        }

        invokerMap.get(eventClz).add(invoker);
    }


    @Override
    public void syncSubmit(IEvent event) {
        doSubmit(event);
    }

    @Override
    public void asyncSubmit(IEvent event) {
        //TODO
    }

    private void doSubmit(IEvent event) {
        if (!invokerMap.containsKey(event.getClass())) {
            return;
        }

        List<IEventInvoker> eventInvokers = invokerMap.get(event.getClass());
        for (IEventInvoker eventInvoker : eventInvokers) {
            eventInvoker.invoke(event);
        }
    }

}
