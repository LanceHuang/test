package com.lance.test.benchmark;

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
import sun.misc.Contended;

import java.util.concurrent.TimeUnit;

/**
 * 伪共享压测
 *
 * @author Lance
 * @since 2022/3/3
 */
@BenchmarkMode(Mode.Throughput) // 单位ms/op，每轮迭代的平均耗时，每轮默认迭代1000ms
//@State(Scope.Thread) // 作用域为thread，表示每条线程都会拥有自己的成员变量
@Fork(1) // fork多少条子进程来执行
//@Threads(2) // 创建多少条线程来执行
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 输出时间单位
@Warmup(iterations = 3) // 预热3轮
@Measurement(iterations = 5) // 测试5轮
public class FalseSharingJmhTest {

    @Benchmark
    @Group("Data")
    public void writeData(Data d) {
        d.writeNum++;
    }

    @Benchmark
    @Group("Data")
    public int readData(Data d) {
        return d.readNum;
    }

    @Benchmark
    @Group("PaddingData")
    public void writePaddingData(PaddingData d) {
        d.writeNum++;
    }

    @Benchmark
    @Group("PaddingData")
    public int readPaddingData(PaddingData d) {
        return d.readNum;
    }

    @Benchmark
    @Group("ContendedData")
    public void writeContendedData(ContendedData d) {
        d.writeNum++;
    }

    @Benchmark
    @Group("ContendedData")
    public int readContendedData(ContendedData d) {
        return d.readNum;
    }

    @State(Scope.Group)
    public static class Data {
        volatile int writeNum;
        volatile int readNum;
    }

    @State(Scope.Group)
    public static class PaddingData {
        long p1, p2, p3, p4, p5, p6, p7;
        volatile int writeNum;
        volatile int readNum;

        // 等价于
//        volatile int writeNum;
//        volatile long p1, p2, p3, p4, p5, p6, p7;
//        volatile int readNum;
    }

    /**
     * 负提升
     */
    @State(Scope.Group)
    public static class ContendedData {
        @Contended
        volatile int writeNum;
        @Contended
        volatile int readNum;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(FalseSharingJmhTest.class.getSimpleName())
                .threads(Runtime.getRuntime().availableProcessors())
                .build();
        new Runner(opt).run();
    }
}
