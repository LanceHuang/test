package com.lance.test.common.concurrent;

import com.lance.common.tool.ThreadUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompleteFuture是为了解决Future没有回调的缺陷
 *
 * @author Lance
 * @since 2021/3/25
 */
public class CompletableFutureTest {

    @Test
    public void testCompletableFuture() {
        // 创建执行器
        ExecutorService executor = Executors.newSingleThreadExecutor();
        // 创建任务，并将Future包装成CompletableFuture
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this::produce, executor);
        // 处理结果
        completableFuture.whenComplete((data, exception) -> System.out.println("Data: " + data));
    }

    private String produce() {
        ThreadUtils.sleep(500L);
        return "Lance";
    }
}
