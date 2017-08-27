package com.lance.test.common.util;

/**
 * @author Lance
 * @since 2017/4/10
 */
public interface Sortable {
    <T extends Comparable> void sort(T[] arr);
}
