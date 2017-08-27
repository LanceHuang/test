package com.lance.test.common.reflect;

import org.junit.Test;

/**
 * @author Lance
 * @date 2016/10/11
 */
public class ArrayTest {

    @Test
    public void test() {
        System.out.println(new int[0].getClass());
        System.out.println(new long[0].getClass());
        System.out.println(new double[0].getClass());
        System.out.println(new float[0].getClass());
        System.out.println(new Integer[0].getClass());
    }
}
