package com.lance.test.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author Lance
 * @since 2022/1/24
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(Thread.currentThread() + " receive " + event.getValue() + " " + sequence + " " + endOfBatch);
    }
}
