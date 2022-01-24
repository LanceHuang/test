package com.lance.test.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author Lance
 * @since 2022/1/24
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        // 获取下一个事件槽
        long sequence = ringBuffer.next();
        try {
            // 获取相应事件槽的事件对象
            LongEvent event = ringBuffer.get(sequence);
            // 读取生产数据，并赋值给事件对象
            event.setValue(bb.getLong(0));
        } finally {
            // 发布事件
            ringBuffer.publish(sequence);
        }
    }
}
