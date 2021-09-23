package com.lance.test.common.balancedbinarytree;

import lombok.Getter;
import lombok.Setter;

/**
 * 二叉树搜索树
 * <ul>
 *     <li>插入：O(lgn)</li>
 *     <li>删除：O(lgn)</li>
 *     <li>查询指定数据：O(lgn)</li>
 *     <li>查询结点数：O(1)</li>
 * </ul>
 *
 * @param <T> 数据类型
 * @author Lance
 * @since 2021/9/22
 */
public class BalancedBinaryTree<T extends Comparable<T>> {

    private static final int LEFT = 1;
    private static final int RIGHT = 2;

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

        // 平衡结点
        node = afterInsert(node, data);

        // 更新高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return node;
    }

    private Node<T> afterInsert(Node<T> node, T data) {
        if (isBalanced(node)) {
            return node;
        }

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            int leftCmp = data.compareTo(node.left.data);
            if (leftCmp < 0) {
                // LL
                node = rightRotate(node);
            } else {
                // LR
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }
        } else if (cmp > 0) {
            int rightCmp = data.compareTo(node.right.data);
            if (rightCmp < 0) {
                // RL
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            } else {
                // RR
                node = leftRotate(node);
            }
        }
        return node;
    }

    private boolean isBalanced(Node<T> node) {
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.abs(leftHeight - rightHeight) <= 1;
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

    /**
     * 右旋
     *
     * @param node 失衡结点
     */
    private Node<T> rightRotate(Node<T> node) {
        Node<T> leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        return leftNode;
    }

    /**
     * 左旋
     *
     * @param node 失衡结点
     */
    private Node<T> leftRotate(Node<T> node) {
        Node<T> rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        return rightNode;
    }

    /**
     * 判断数据是否存在
     *
     * @param data 数据
     * @return 判断结果
     */
    public boolean contains(T data) {
        if (root == null) {
            return false;
        }

        return contains(root, data);
    }

    public boolean contains(Node<T> node, T data) {
        int compareResult = node.data.compareTo(data);
        if (compareResult > 0) {
            if (node.getLeft() == null) {
                return false;
            } else {
                return contains(node.getLeft(), data);
            }
        } else if (compareResult < 0) {
            if (node.getRight() == null) {
                return false;
            } else {
                return contains(node.getRight(), data);
            }
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
        if (remove(null, root, LEFT, data)) {
            count--;
        }
    }

    private boolean remove(Node<T> parent, Node<T> node, int child, T data) {
        if (node == null) {
            return false;
        }

        int compareResult = node.getData().compareTo(data);
        if (compareResult > 0) {
            if (node.getLeft() == null) {
                return false;
            } else {
                return remove(node, node.getLeft(), LEFT, data);
            }
        } else if (compareResult < 0) {
            if (node.getRight() == null) {
                return false;
            } else {
                return remove(node, node.getRight(), RIGHT, data);
            }
        } else {
            Node<T> replaceNode;
            if (node.getLeft() == null && node.getRight() == null) {
                replaceNode = null;
            } else if (node.getLeft() != null && node.getRight() == null) {
                replaceNode = node.getLeft();
            } else if (node.getLeft() == null && node.getRight() != null) {
                replaceNode = node.getRight();
            } else {
                replaceNode = getMin(node.getRight());
                if (replaceNode != null) {
                    remove(node, node.getRight(), RIGHT, replaceNode.getData());
                }
            }

            if (parent != null) {
                if (child == LEFT) {
                    parent.setLeft(replaceNode);
                } else {
                    parent.setRight(replaceNode);
                }
            }
            return true;
        }
    }

    /**
     * 取最大值
     *
     * @return 最大值
     */
    public T getMax() {
        Node<T> maxNode = getMax(root);
        return maxNode == null ? null : maxNode.getData();
    }

    private Node<T> getMax(Node<T> node) {
        if (node == null) {
            return null;
        }

        if (node.getRight() != null) {
            return getMax(node.getRight());
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
        return minNode == null ? null : minNode.getData();
    }

    private Node<T> getMin(Node<T> node) {
        if (node == null) {
            return null;
        }

        if (node.getLeft() != null) {
            return getMin(node.getLeft());
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
     * 计算
     *
     * @return 层数
     */
    public int getLevel() {
        return getLevel(root, 0);
    }

    private int getLevel(Node<T> node, int level) {
        if (node == null) {
            return level;
        }
        int leftLevel = getLevel(node.getLeft(), level + 1);
        int rightLevel = getLevel(node.getRight(), level + 1);
        return Math.max(leftLevel, rightLevel);
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

        logNode(node.getLeft(), level + 1);

        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(node.getData());

        logNode(node.getRight(), level + 1);
    }

    @Getter
    @Setter
    public static class Node<T> {

        private T data;

        private Node<T> left;

        private Node<T> right;

        private int height;

        public Node(T data) {
            this.data = data;
        }
    }

}
