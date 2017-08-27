package com.lance.test.common.util;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Lance
 * @date 2016/10/4
 */
public class ArraysTest {

    @Test
    public void testCopy() {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6};
        int[] newNums = Arrays.copyOfRange(nums, 1, 4);

        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(newNums));
    }

    @Test
    public void testCopyOf() {
        int[] nums1 = new int[]{0, 1, 2, 3, 4, 5, 6};
        int[] nums2 = Arrays.copyOf(nums1, nums1.length);

        nums2[0] = 10;
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
    }
}
