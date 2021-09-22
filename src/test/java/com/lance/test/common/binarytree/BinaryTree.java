package com.lance.test.common.binarytree;

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
public class BinaryTree<T extends Comparable<T>> {

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
        if (root == null) {
            root = new Node<>(data);
            count++;
        } else {
            if (insert(root, data)) {
                count++;
            }
        }


    }

    private boolean insert(Node<T> node, T data) {
        int compareResult = node.getData().compareTo(data);
        if (compareResult > 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(data));
                return true;
            } else {
                return insert(node.getLeft(), data);
            }
        } else if (compareResult < 0) {
            if (node.getRight() == null) {
                node.setRight(new Node<>(data));
                return true;
            } else {
                return insert(node.getRight(), data);
            }
        }
        return false;
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
        } else {
            return contains(root, data);
        }
    }

    public boolean contains(Node<T> node, T data) {
        int compareResult = node.getData().compareTo(data);
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
}
