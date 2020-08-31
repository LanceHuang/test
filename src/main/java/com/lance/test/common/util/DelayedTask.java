package com.lance.test.common.util;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时任务
 *
 * @author Lance
 */
public class DelayedTask implements Delayed {

    /** 任务名称 */
    private String name;

    /** 延迟时间（毫秒） */
    private long delayTime;

    /** 执行时间（纳秒） */
    private long workTime;

    public DelayedTask(String name, long delayTime) {
        this.name = name;
        this.delayTime = delayTime;
        this.workTime = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(delayTime); // 将毫秒转换成纳秒，计算任务执行时间
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(workTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed task) {
        DelayedTask delayedTask = (DelayedTask) task;
        return Long.compare(this.workTime, delayedTask.workTime); // 执行时间早的任务排前面
    }

    @Override
    public String toString() {
        return "DelayedTask{" +
                "name='" + name + '\'' +
                ", delayTime=" + delayTime +
                ", workTime=" + workTime +
                '}';
    }
}
