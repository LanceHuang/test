package com.lance.test.common.util;

/**
 * @author Lance
 * @since 2017/4/10
 */
public abstract class AbstractSorter implements Sortable {
    public <T extends Comparable> void swap(int fromIndex, int toIndex, T[] arr) {
        T tmp = arr[fromIndex];
        arr[fromIndex] = arr[toIndex];
        arr[toIndex] = tmp;
    }
}
