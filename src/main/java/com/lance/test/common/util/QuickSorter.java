package com.lance.test.common.util;

/**
 * 快速排序：指定数组中的某个元素。将所有小于或等于该元素的值移动到该元素之前，将大于该元素的值移动到该元素之后。
 *
 * @author Lance
 * @since 2017/4/10
 */
public class QuickSorter extends AbstractSorter {

    @Override
    public <T extends Comparable> void sort(T[] arr) {
        sort(0, arr.length - 1, arr);
    }

    public <T extends Comparable> void sort(int fromIndex, int toIndex, T[] arr) {
        if (fromIndex >= toIndex) return;

        int index = fromIndex;
        T tmp = arr[fromIndex];
        int left = fromIndex, right = toIndex;

        while (left < right) {
            while (left < right && tmp.compareTo(arr[right]) <= 0) {
                right--;
            }

            if (left <= right) {
                swap(index, right, arr);
                tmp = arr[right];
                index = right;
                left++;
            }

            while (left < right && arr[left].compareTo(tmp) <= 0) {
                left++;
            }

            if (left <= right) {
                swap(left, index, arr);
                tmp = arr[left];
                index = left;
                right--;
            }
        }

        //Split
        sort(fromIndex, index - 1, arr);
        sort(index + 1, toIndex, arr);
    }

}
