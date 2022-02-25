package com.lance.test.disruptor;

import com.lance.common.tool.ThreadUtils;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadFactory;

/**
 * @author Lance
 * @since 2022/1/24
 */
public class DisruptorTest {

    @Test
    void test() {
        // 初始化配置
        EventFactory<PayloadEvent> eventFactory = PayloadEvent::new;
        int ringBufferSize = 1024;
        ThreadFactory threadFactory = ThreadUtils.nameThreadFactory("event");
        ProducerType producerType = ProducerType.SINGLE;
        WaitStrategy waitStrategy = new YieldingWaitStrategy();
        EventHandler<PayloadEvent> eventHandler = (event, sequence, endOfBatch) -> System.out.println("Handle: " + event.getPayload());
        EventTranslatorOneArg<PayloadEvent, Object> translator = (event, sequence, arg0) -> event.setPayload(arg0);

        // 启动
        Disruptor<PayloadEvent> disruptor = new Disruptor<>(
                eventFactory, ringBufferSize, threadFactory,
                producerType, waitStrategy
        );
        disruptor.handleEventsWith(eventHandler);
        disruptor.start();

        // 生产数据
        for (int i = 0; i < 108; i++) {
            disruptor.publishEvent(translator, "data" + i);
        }

        // 关闭
        disruptor.shutdown();
    }

    @Data
    public static class PayloadEvent {

        private Object payload;
    }
}
