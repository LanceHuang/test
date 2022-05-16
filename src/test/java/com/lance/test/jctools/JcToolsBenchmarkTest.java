package com.lance.test.jctools;

import org.jctools.queues.MpmcArrayQueue;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * jctools工具压测
 *
 * @author Lance
 * @since 2022/4/19
 */
@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
@Fork(1) // fork多少条子进程来执行
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 输出时间单位
@Warmup(iterations = 3) // 预热3轮
@Measurement(iterations = 5) // 测试5轮
public class JcToolsBenchmarkTest {

    /*
        Benchmark                                         Mode  Cnt       Score       Error   Units
        JcToolsTest.JcData                               thrpt    5  148271.780 ± 13732.990  ops/ms
        JcToolsTest.JcData:testMpmcArrayQueueRead        thrpt    5   73381.441 ± 10125.178  ops/ms
        JcToolsTest.JcData:testMpmcArrayQueueWrite       thrpt    5   74890.339 ± 14127.737  ops/ms
        JcToolsTest.JdkData                              thrpt    5   33643.560 ±  4436.326  ops/ms
        JcToolsTest.JdkData:testArrayBlockingQueueRead   thrpt    5   17264.636 ±  2685.904  ops/ms
        JcToolsTest.JdkData:testArrayBlockingQueueWrite  thrpt    5   16378.925 ±  4087.615  ops/ms
     */

    @State(Scope.Group)
    public static class JdkData {
        public ArrayBlockingQueue<Integer> data = new ArrayBlockingQueue<>(1024);
    }

    @Benchmark
    @Group("JdkData")
    public Integer testArrayBlockingQueueRead(JdkData data) {
        return data.data.poll();
    }

    @Benchmark
    @Group("JdkData")
    public void testArrayBlockingQueueWrite(JdkData data) {
        data.data.offer(10);
    }

    @State(Scope.Group)
    public static class JcData {
        public MpmcArrayQueue<Integer> data = new MpmcArrayQueue<>(1024);
    }

    @Benchmark
    @Group("JcData")
    public Integer testMpmcArrayQueueRead(JcData data) {
        return data.data.poll();
    }

    @Benchmark
    @Group("JcData")
    public void testMpmcArrayQueueWrite(JcData data) {
        data.data.offer(10);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JcToolsBenchmarkTest.class.getSimpleName())
                .threads(Runtime.getRuntime().availableProcessors())
                .build();
        new Runner(opt).run();
    }
}

