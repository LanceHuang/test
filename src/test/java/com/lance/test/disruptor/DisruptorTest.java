package com.lance.test.disruptor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;

/**
 * @author Lance
 * @since 2022/1/24
 */
public class DisruptorTest {

    @Test
    void test() {
        // 创建Disruptor
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        int ringBufferSize = 1024;
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("event-%d").build();
        ProducerType producerType = ProducerType.SINGLE;
        WaitStrategy waitStrategy = new YieldingWaitStrategy();
        Disruptor<LongEvent> disruptor = new Disruptor<>(
                eventFactory, ringBufferSize, threadFactory,
                producerType, waitStrategy
        );

        // 注册消息处理者
        EventHandler<LongEvent> eventHandler = new LongEventHandler();
        disruptor.handleEventsWith(eventHandler);

        // 启动Disruptor
        disruptor.start();

        // 创建生产者
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer eventProducer = new LongEventProducer(ringBuffer);

        // 生产数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread() + " produce " + i);
            byteBuffer.putLong(0, i);
            eventProducer.onData(byteBuffer);
        }

        // 关闭Disruptor
        disruptor.shutdown();
    }
}
