package com.lance.test.spring.lifecycle;

import org.springframework.context.SmartLifecycle;

/**
 * @author Lance
 */
public class PhaseBean1 implements SmartLifecycle {

    private boolean running = false;

    @Override
    public void start() {
        if (this.running) {
            return;
        }

        System.out.println("PhaseBean1 start");
        this.running = true;
    }

    @Override
    public void stop() {
        if (!this.running) {
            return;
        }

        System.out.println("PhaseBean1 stop");
        this.running = false;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }
}
