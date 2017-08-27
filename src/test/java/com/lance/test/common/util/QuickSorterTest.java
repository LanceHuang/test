package com.lance.test.common.util;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Lance
 * @since 2017/4/10
 */
public class QuickSorterTest {

    @Test
    public void sort() throws Exception {
        Double[] arr = {5.90, 22.3, 4.2, 89.5, 3.2, 565.12, 55.4};

        Sortable sorter = new QuickSorter();
        sorter.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}