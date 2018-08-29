package com.lance.test.common.concurrent;

import com.lance.test.common.util.DelayedTask;
import org.junit.Test;

import java.util.concurrent.DelayQueue;

public class DelayedTest {

    @Test
    public void test() throws InterruptedException {
        DelayQueue<DelayedTask> aQueue = new DelayQueue<>();
        aQueue.add(new DelayedTask("name1", 5));
        aQueue.add(new DelayedTask("name2", 1));
        aQueue.add(new DelayedTask("name3", 2));
        aQueue.add(new DelayedTask("name4", 8));

        while (!aQueue.isEmpty()) {
            System.out.println(aQueue.take());
        }
        /*
        DelayedTask{name='name2', delayTime=1, workTime=951063833871385}
        DelayedTask{name='name3', delayTime=2, workTime=951064833918205}
        DelayedTask{name='name1', delayTime=5, workTime=951067832432048}
        DelayedTask{name='name4', delayTime=8, workTime=951070833945013}
         */
    }

}
