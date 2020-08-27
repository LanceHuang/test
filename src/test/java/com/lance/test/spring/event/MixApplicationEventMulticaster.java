package com.lance.test.spring.event;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.ResolvableType;

/**
 * 混合事件多播器
 *
 * @author Lance
 */
public class MixApplicationEventMulticaster implements ApplicationEventMulticaster {

    private ApplicationEventMulticaster syncMulticaster;

    private ApplicationEventMulticaster asyncMulticaster;

    public MixApplicationEventMulticaster(ApplicationEventMulticaster syncMulticaster, ApplicationEventMulticaster asyncMulticaster) {
        this.syncMulticaster = syncMulticaster;
        this.asyncMulticaster = asyncMulticaster;
    }

    @Override
    public void addApplicationListener(@NonNull ApplicationListener<?> listener) {
        this.syncMulticaster.addApplicationListener(listener);
        this.asyncMulticaster.addApplicationListener(listener);
    }

    @Override
    public void addApplicationListenerBean(@NonNull String listenerBeanName) {
        this.syncMulticaster.addApplicationListenerBean(listenerBeanName);
        this.asyncMulticaster.addApplicationListenerBean(listenerBeanName);
    }

    @Override
    public void removeApplicationListener(@NonNull ApplicationListener<?> listener) {
        this.syncMulticaster.removeApplicationListener(listener);
        this.asyncMulticaster.removeApplicationListener(listener);
    }

    @Override
    public void removeApplicationListenerBean(@NonNull String listenerBeanName) {
        this.syncMulticaster.removeApplicationListenerBean(listenerBeanName);
        this.asyncMulticaster.removeApplicationListenerBean(listenerBeanName);
    }

    @Override
    public void removeAllListeners() {
        this.syncMulticaster.removeAllListeners();
        this.asyncMulticaster.removeAllListeners();
    }

    @Override
    public void multicastEvent(@NonNull ApplicationEvent event) {
        getMulticaster(event).multicastEvent(event);
    }

    @Override
    public void multicastEvent(@NonNull ApplicationEvent event, ResolvableType eventType) {
        getMulticaster(event).multicastEvent(event, eventType);
    }

    private ApplicationEventMulticaster getMulticaster(ApplicationEvent event) {
        if (event instanceof MixApplicationEvent) {
            MixApplicationEvent mixApplicationEvent = (MixApplicationEvent) event;
            return mixApplicationEvent.getType() == EventType.SYNC ? this.syncMulticaster : this.asyncMulticaster;
        }
        return this.syncMulticaster;
    }
}
