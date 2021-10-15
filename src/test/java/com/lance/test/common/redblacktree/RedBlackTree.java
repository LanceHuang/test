package com.lance.test.common.redblacktree;

import lombok.Getter;
import lombok.Setter;

/**
 * 红黑树
 *
 * @author Lance
 * @since 2021/10/14
 */
public class RedBlackTree<T extends Comparable<T>> {

    private Node<T> root;

    private int count;

    /**
     * 插入数据
     *
     * @param data 数据
     */
    public void insert(T data) {
        if (data == null) {
            return;
        }

        root = insert(root, data);
        root.red = false;
    }

    private Node<T> insert(Node<T> node, T data) {
        if (node == null) {
            count++;
            Node<T> newNode = new Node<>(data);
            newNode.red = true;
            return newNode;
        }

        // 插入结点
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert(node.left, data);
        } else if (cmp > 0) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }

        // 平衡结点
        node = balanceNode(node);
        return node;
    }

    private Node<T> balanceNode(Node<T> node) {
        if (node == null) {
            return null;
        }

        if (!isRed(node.left) && isRed(node.right)) {
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private boolean isRed(Node<T> node) {
        if (node == null) {
            return false;
        }
        return node.red;
    }

    /**
     * 判断数据是否存在
     *
     * @param data 数据
     * @return 判断结果
     */
    public boolean contains(T data) {
        if (data == null) {
            return false;
        }

        return contains(root, data);
    }

    public boolean contains(Node<T> node, T data) {
        if (node == null) {
            return false;
        }

        int compareResult = data.compareTo(node.data);
        if (compareResult < 0) {
            return contains(node.left, data);
        } else if (compareResult > 0) {
            return contains(node.right, data);
        } else {
            return true;
        }
    }

    /**
     * 删除数据
     *
     * @param data 数据
     */
    public void remove(T data) {
        if (data == null) {
            return;
        }

        root = remove(root, data, false);
    }

    private Node<T> remove(Node<T> node, T data, boolean move) {
        if (node == null) {
            return null;
        }

//        int compareResult = data.compareTo(node.data);
//        if (compareResult < 0) {
//            node.left = remove(node.left, data, move);
//        } else if (compareResult > 0) {
//            node.right = remove(node.right, data, move);
//        } else {
//            if (node.left == null && node.right == null) {
//                node = null;
//            } else if (node.left != null && node.right == null) {
//                node = node.left;
//            } else if (node.left == null && node.right != null) {
//                node = node.right;
//            } else {
//                // 删除右子树最小的结点
//                Node<T> replaceNode = getMin(node.right);
//                remove(node.right, replaceNode.data, true);
//
//                // 替换原来的结点
//                replaceNode.left = node.left;
//                if (replaceNode != node.right) {
//                    replaceNode.right = node.right;
//                }
//                node = replaceNode;
//            }
//
//            if (!move) {
//                // 更新数量
//                count--;
//            }
//        }
//
//        // 更新高度
//        updateHeight(node);
//        // 平衡结点
//        node = balanceNode(node);
        return node;
    }

    /**
     * 右旋
     *
     * @param node 失衡结点
     */
    private Node<T> rightRotate(Node<T> node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        }

        Node<T> leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        leftNode.red = node.red;
        node.red = true;
        return leftNode;
    }

    /**
     * 左旋
     *
     * @param node 失衡结点
     */
    private Node<T> leftRotate(Node<T> node) {
        if (node == null) {
            return null;
        }

        if (node.right == null) {
            return node;
        }

        Node<T> rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        rightNode.red = node.red;
        node.red = true;
        return rightNode;
    }

    private void flipColors(Node<T> node) {
        node.red = true;
        node.left.red = false;
        node.right.red = false;
    }

    /**
     * 计算结点数
     *
     * @return 结点数
     */
    public int getCount() {
        return count;
    }

    /**
     * 打印
     */
    public void logTree() {
        logNode(root, 0);
    }

    private void logNode(Node<T> node, int level) {
        if (node == null) {
            return;
        }

        logNode(node.left, level + 1);

        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(node.data + "(" + (node.red ? "Red" : "Black") + ")");

        logNode(node.right, level + 1);
    }

    @Getter
    @Setter
    public static class Node<T> {

        private T data;

        private Node<T> left;

        private Node<T> right;

        private boolean red;

        public Node(T data) {
            this.data = data;
        }
    }
}
