package com.lance.test.fiber;

import co.paralleluniverse.fibers.Fiber;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

/**
 * 协程。启动参数：
 * <pre>
 * -javaagent:C:\software\apache-maven-3.5.4\repo\co\paralleluniverse\quasar-core\0.7.9\quasar-core-0.7.9-jdk8.jar
 * </pre>
 *
 * @author Lance
 * @since 2022/3/30
 */
public class FiberTest {

    @Test
    public void test() throws Exception {
        int n = 100000;
        long t1 = System.currentTimeMillis();
        runFibers(n);
        long t2 = System.currentTimeMillis();
        System.out.println("Cost in " + (t2 - t1) + "ms");
    }

    @SuppressWarnings("unchecked")
    private static void runFibers(int n) throws Exception {
        Fiber<Void>[] fibers = new Fiber[n];
        for (int i = 0; i < n; i++) {
            fibers[i] = new Fiber<>(() -> Fiber.sleep(1000L));
        }

        for (int i = 0; i < n; i++) {
            fibers[i].start();
        }

        for (int i = 0; i < n; i++) {
            fibers[i].join();
        }
    }

    @Test
    public void testFiberName() throws ExecutionException, InterruptedException {
        Fiber<Void> fiber1 = new Fiber<>(() -> {
            System.out.println("Fiber1 name: " + Fiber.currentFiber().getName());
            System.out.println("Fiber1 thread name: " + Thread.currentThread().getName());
        });
        Fiber<Void> fiber2 = new Fiber<>(() -> {
            System.out.println("Fiber2 name: " + Fiber.currentFiber().getName());
            System.out.println("Fiber2 thread name: " + Thread.currentThread().getName());
        });

        fiber1.start();
        fiber2.start();
        fiber1.join();
        fiber2.join();

//        Fiber1 name: fiber-10000001
//        Fiber1 thread name: ForkJoinPool-default-fiber-pool-worker-1
//        Fiber2 name: fiber-10000002
//        Fiber2 thread name: ForkJoinPool-default-fiber-pool-worker-1
    }
}
