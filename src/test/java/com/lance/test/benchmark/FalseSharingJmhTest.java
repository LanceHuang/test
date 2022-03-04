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

    /**
     * 常规padding写法
     */
    @State(Scope.Group)
    public static class DataPad {
        protected long p1, p2, p3, p4, p5, p6, p7, p8;
        protected volatile int writeNum;
        protected long p9, p10, p11, p12, p13, p14, p15, p16;
        protected volatile int readNum;
    }

    @Benchmark
    @Group("pad")
    public void writePad(DataPad d) {
        d.writeNum++;
    }

    @Benchmark
    @Group("pad")
    public int readPad(DataPad d) {
        return d.readNum;
    }

    /**
     * 普通类
     */
    @State(Scope.Group)
    public static class DataNotPad {
        protected volatile int writeNum;
        protected volatile int readNum;
    }

    @Benchmark
    @Group("nopad")
    public void writeNoPad(DataNotPad d) {
        d.writeNum++;
    }

    @Benchmark
    @Group("nopad")
    public int readNoPad(DataNotPad d) {
        return d.readNum;
    }

    public static class BufferRhsPad {
        protected long p1, p2, p3, p4, p5, p6, p7, p8;
    }

    public static class BufferField extends BufferRhsPad {
        protected volatile int writeNum;
    }

    public static class BufferFieldWithLhs extends BufferField {
        protected long p9, p10, p11, p12, p13, p14, p15, p16;
    }

    /**
     * 继承Padding写法
     */
    @State(Scope.Group)
    public static class BufferData extends BufferFieldWithLhs {
        protected volatile int readNum;
    }

    @Benchmark
    @Group("buffer")
    public void writeBufferDataPad(BufferData d) {
        d.writeNum++;
    }

    @Benchmark
    @Group("buffer")
    public int readBufferDataPad(BufferData d) {
        return d.readNum;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(FalseSharingJmhTest.class.getSimpleName())
                .threads(Runtime.getRuntime().availableProcessors())
                .build();
        new Runner(opt).run();
    }
}
