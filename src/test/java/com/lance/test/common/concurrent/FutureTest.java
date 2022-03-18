package com.lance.test.common.concurrent;

import com.lance.common.tool.ThreadUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 异步消费
 *
 * @author Lance
 * @since 2022/3/18
 */
public class FutureTest {

    @Test
    public void testFuture() throws Exception {
        // 创建Future
        FutureTask<String> f = new FutureTask<>(this::runTask);
        // 创建线程运行任务
        new Thread(f).start();
        // 等待处理结果
        System.out.println(f.get());
    }

    private String runTask() {
        // 模拟耗时操作
        ThreadUtils.sleep(1000L);
        // 生产数据
        return "Lance";
    }

    @Test
    public void testFutureGet() throws Exception {
        // 创建执行器
        ExecutorService executor = Executors.newSingleThreadExecutor();
        // 创建任务
        Future<String> f = executor.submit(this::produce);
        // 等待处理结果，最大等待时间为1500ms，500ms有结果，所以直接被唤醒了
        System.out.println("Data: " + f.get(1500L, TimeUnit.MILLISECONDS));
        System.out.println("T3: " + System.currentTimeMillis());
    }

    private String produce() {
        System.out.println("T1: " + System.currentTimeMillis());
        ThreadUtils.sleep(500L);
        System.out.println("T2: " + System.currentTimeMillis());
        return "Lance";
    }
}
