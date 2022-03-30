package com.lance.test.aparapi;

import com.aparapi.Kernel;

/**
 * @author Lance
 * @since 2022/3/29
 */
public class RangeCounter extends Kernel {

    private final float[] inA;
    private final float[] inB;
    private final float[] result;
    private final int iteration;

    public RangeCounter(int n, int iteration) {
        this.inA = new float[n];
        this.inB = new float[n];
        this.result = new float[n];
        this.iteration = iteration;
    }

    @Override
    public void run() {
        int i = getGlobalId();
        for (int j = 0; j < iteration; j++) {
            result[i] = result[i] + inA[i] + inB[i] + 1;
        }
    }

    public float[] getResult() {
        return result;
    }
}
