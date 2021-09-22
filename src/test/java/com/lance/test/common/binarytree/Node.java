package com.lance.test.common.binarytree;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Lance
 * @since 2021/9/22
 */
@Getter
@Setter
public class Node<T extends Comparable<T>> {

    private T data;

    private Node<T> left;

    private Node<T> right;

    public Node(T data) {
        this.data = data;
    }
}
