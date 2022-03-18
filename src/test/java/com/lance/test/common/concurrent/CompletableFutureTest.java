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
    public void testCompletableFuture() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this::produce, executor);
        completableFuture.thenAccept((data) -> System.out.println("Data: " + data))
                .thenRun(() -> System.out.println("Finished"));
        completableFuture.get();
        executor.shutdown();
    }

    private String produce() {
        ThreadUtils.sleep(500L);
        return "Lance";
    }

    @Test
    public void testAllOf() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> runTask("task1"), executor);
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> runTask("task2"), executor);
        CompletableFuture<Void> f3 = CompletableFuture.runAsync(() -> runTask("task3"), executor);

        // 等待以上3个任务都执行完成
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(f1, f2, f3)
                .thenRun(() -> System.out.println("Finished"));
        completableFuture.get();
        executor.shutdown();
    }

    private void runTask(String taskName) {
        ThreadUtils.sleep(500L);
        System.out.println("Run: " + taskName);
    }
}
