package com.lance.test.common.concurrent;

import com.lance.common.tool.util.TimeUtils;
import com.lance.test.common.util.DelayedTask;
import org.junit.Test;

import java.util.concurrent.DelayQueue;

/**
 * @author Lance
 */
public class DelayedTest {

    @Test
    public void test() throws InterruptedException {
        DelayQueue<DelayedTask> aQueue = new DelayQueue<>();
        aQueue.add(new DelayedTask("name1", 5000L));
        aQueue.add(new DelayedTask("name2", 1000L));
        aQueue.add(new DelayedTask("name3", 2000L));
        aQueue.add(new DelayedTask("name4", 8000L));

        while (!aQueue.isEmpty()) {
            System.out.println(aQueue.take());
        }
    }
}
