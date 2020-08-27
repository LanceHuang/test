package com.lance.test.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * spring自带的事件框架
 *
 * @author Lance
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationEventPublisherTest.class)
public class ApplicationEventPublisherTest {

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @EventListener
    public void testEvent(TestEvent event) { // 接收并处理事件，同步
        System.out.println("Receive TestEvent");
    }

    @Test
    public void test() {
        eventPublisher.publishEvent(new TestEvent()); // 发布事件
        System.out.println("Hello world");
    }
}
