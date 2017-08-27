package com.lance.test.common.lang;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Lance
 * @date 2016/10/4
 */
public class SystemTest {

    @Test
    public void testArraycopy() {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6};
        int[] newNums = new int[nums.length];

        System.arraycopy(nums, 1, newNums, 0, 3);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(newNums));
    }

    @Test
    public void testGetenv(){
        System.out.println(System.getenv("path"));
    }
}
