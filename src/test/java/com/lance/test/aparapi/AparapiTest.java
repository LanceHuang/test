package com.lance.test.aparapi;

import com.aparapi.Kernel;
import com.aparapi.Range;
import org.junit.jupiter.api.Test;

/**
 * 调用GPU进行计算
 *
 * @author Lance
 * @since 2022/3/28
 */
public class AparapiTest {

    private final int N = 1024;
    private final int ITERATION = 1000000;

    @Test
    void testPlainCal() {
        float[] inA = new float[N];
        float[] inB = new float[N];
        float[] result = new float[N];

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < ITERATION; j++) {
                result[i] = result[i] + inA[i] + inB[i] + 1;
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Cost in " + (t2 - t1) + "ms");
    }

    @Test
    void testAparapi() {
        // 预热：预编译代码，将字节码转换成OpenCL码
        Kernel kernel = new RangeCounter(1, 0);
        kernel.execute(Range.create(1));
        kernel.dispose();

        // 计算
        long t1 = System.currentTimeMillis();
        RangeCounter counter = new RangeCounter(N, ITERATION);
        counter.execute(Range.create(N));
        counter.dispose();
        long t2 = System.currentTimeMillis();
        System.out.println("Cost in " + (t2 - t1) + "ms");
    }

}
