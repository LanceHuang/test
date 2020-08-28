package com.lance.test.spring.event;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.ResolvableType;

/**
 * 混合事件多播器
 *
 * @author Lance
 */
public class MixApplicationEventMulticaster extends SimpleApplicationEventMulticaster {

    public MixApplicationEventMulticaster() {
    }

    public MixApplicationEventMulticaster(BeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event, ResolvableType eventType) {
        if (event instanceof MixApplicationEvent && !((MixApplicationEvent) event).isSync()) { // 异步事件
            super.multicastEvent(event, eventType);
            return;
        }

        // 普通事件和同步事件都不调用Executor
        ResolvableType type = (eventType != null ? eventType : resolveDefaultEventType(event));
        for (ApplicationListener<?> listener : getApplicationListeners(event, type)) {
            invokeListener(listener, event);
        }
    }

    private ResolvableType resolveDefaultEventType(ApplicationEvent event) {
        return ResolvableType.forInstance(event);
    }
}