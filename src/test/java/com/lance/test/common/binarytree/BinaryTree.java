package com.lance.test.common.binarytree;

import lombok.Getter;
import lombok.Setter;

/**
 * 二叉树
 * <ul>
 *     <li>插入：O(lgn)~O(n)</li>
 *     <li>删除：O(lgn)</li>
 *     <li>查询指定数据：O(lgn)~O(n)</li>
 *     <li>查询结点数：O(1)</li>
 * </ul>
 *
 * @param <T> 数据类型
 * @author Lance
 * @since 2021/9/22
 */
public class BinaryTree<T extends Comparable<T>> {

    /** 根结点 */
    private Node<T> root;

    /** 结点数 */
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
    }

    private Node<T> insert(Node<T> node, T data) {
        if (node == null) {
            count++;
            return new Node<>(data);
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
        // 更新高度
        updateHeight(node);

        return node;
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

        int compareResult = data.compareTo(node.data);
        if (compareResult < 0) {
            node.left = remove(node.left, data, move);
        } else if (compareResult > 0) {
            node.right = remove(node.right, data, move);
        } else {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left != null && node.right == null) {
                node = node.left;
            } else if (node.left == null && node.right != null) {
                node = node.right;
            } else {
                // 删除右子树最小的结点
                Node<T> replaceNode = getMin(node.right);
                remove(node.right, replaceNode.data, true);

                // 替换原来的结点
                replaceNode.left = node.left;
                if (replaceNode != node.right) {
                    replaceNode.right = node.right;
                }
                node = replaceNode;
            }

            if (!move) {
                // 更新数量
                count--;
            }
        }

        // 更新高度
        updateHeight(node);
        return node;
    }

    /**
     * 计算高度
     *
     * @return 高度
     */
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * 计算结点高度
     *
     * @param node 结点
     * @return 高度
     */
    private int getHeight(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getHeightDiff(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return leftHeight - rightHeight;
    }

    /**
     * 更新结点高度
     *
     * @param node 结点
     */
    private void updateHeight(Node<T> node) {
        if (node == null) {
            return;
        }
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    /**
     * 取最大值
     *
     * @return 最大值
     */
    public T getMax() {
        Node<T> maxNode = getMax(root);
        return maxNode == null ? null : maxNode.data;
    }

    private Node<T> getMax(Node<T> node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return getMax(node.right);
        } else {
            return node;
        }
    }

    /**
     * 取最小值
     *
     * @return 最小值
     */
    public T getMin() {
        Node<T> minNode = getMin(root);
        return minNode == null ? null : minNode.data;
    }

    private Node<T> getMin(Node<T> node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            return getMin(node.left);
        } else {
            return node;
        }
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
        System.out.println(node.data + "(" + node.height + ")");

        logNode(node.right, level + 1);
    }

    @Getter
    @Setter
    public static class Node<T> {

        private T data;

        private Node<T> left;

        private Node<T> right;

        private int height = 1;

        public Node(T data) {
            this.data = data;
        }
    }

}
