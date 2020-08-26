package com.lance.test.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.SmartLifecycle;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类似于PostConstruct/PreDestroy的用途，可通过getPhase控制优先级 Integer.MAX_VALUE优先级最低
 *
 * @author Lance
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SmartLifecycleTest.class)
public class SmartLifecycleTest implements SmartLifecycle {

    private boolean running = false;

    @Override
    public void start() {
        if (this.running) {
            return;
        }

        System.out.println("start");
        this.running = true;
    }

    @Override
    public void stop() { // 当isRunning() == true时，才会调用
        if (!this.running) {
            return;
        }

        System.out.println("stop");
        this.running = false;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Test
    public void test() {
        System.out.println("Hello SmartLifecycle");
    }
}
