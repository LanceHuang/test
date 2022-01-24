package com.lance.test.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author Lance
 * @since 2022/1/24
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
