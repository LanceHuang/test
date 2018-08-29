package com.lance.test.common.util;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Delayed {

    private String name;

    private long delayTime;

    private long workTime;

    public DelayedTask(String name, long delayTime) {
        this.name = name;
        this.delayTime = delayTime;
        this.workTime = System.nanoTime() + TimeUnit.SECONDS.toNanos(delayTime);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(workTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask task = (DelayedTask) o;
        if (workTime > task.workTime) {
            return 1;
        } else if (workTime == task.workTime) {
            return 0;
        } else {
            return -1;
        }
    }

//    public abstract void execute();

    @Override
    public String toString() {
        return "DelayedTask{" +
                "name='" + name + '\'' +
                ", delayTime=" + delayTime +
                ", workTime=" + workTime +
                '}';
    }
}
