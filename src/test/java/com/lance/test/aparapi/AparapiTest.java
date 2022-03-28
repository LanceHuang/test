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
        float[] inA = new float[N];
        float[] inB = new float[N];
        float[] result = new float[N];

        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int i = getGlobalId();
                for (int j = 0; j < ITERATION; j++) {
                    result[i] = result[i] + inA[i] + inB[i] + 1;
                }
            }
        };

        // 预热
        kernel.execute(Range.create(1));

        // 计算
        long t1 = System.currentTimeMillis();
        Range range = Range.create(N);
        kernel.execute(range);
        kernel.dispose();
        long t2 = System.currentTimeMillis();
        System.out.println("Cost in " + (t2 - t1) + "ms");
    }

}
