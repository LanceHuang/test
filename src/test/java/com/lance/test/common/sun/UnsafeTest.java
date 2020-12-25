package com.lance.test.common.sun;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Lance
 * @since 2018/11/15 14:34
 */
public class UnsafeTest {

    /** Test data */
    private int state = 0;

    private Unsafe unsafe;
    private long   stateOffset;

    private final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    @Before
    public void before() throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        unsafe = (Unsafe) field.get(null);

        stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
    }

    @Test
    public void test() {
        Assert.assertEquals(state, 0);

        state = 1;
        Assert.assertEquals(state, 1);

        boolean success = compareAndSetState(1, 8);
        Assert.assertTrue(success);
        Assert.assertEquals(state, 8);

        success = compareAndSetState(90, 665);
        Assert.assertFalse(success);
        Assert.assertEquals(state, 8);
        Assert.assertNotSame(state, 665);
    }
}
