package com.lance.test.common.redblacktree;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Lance
 * @since 2021/10/14
 */
public class RedBlackTreeTest {

    @Test
    public void test() {
        // 初始化数据
        int n = 20;
        int range = 100;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = RandomUtils.nextInt(0, range);
        }
        System.out.println("Data: " + Arrays.toString(nums));
        System.out.println();

        // 初始化二叉搜索树
        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();
        for (int num : nums) {
            redBlackTree.insert(num);
//            System.out.println("============================== " + num);
//            binaryTree.logTree();
//            System.out.println("==============================");
        }

        // 查询数据
        int searchNum = 20;
        System.out.println("Search: " + searchNum);
        System.out.println("Search result: " + redBlackTree.contains(searchNum));
        System.out.println("Count: " + redBlackTree.getCount());
//        System.out.println("Height: " + binaryTree.getHeight());
        redBlackTree.logTree();
        System.out.println();

        // 删除数据
        int removeNum = nums[2];
        redBlackTree.remove(removeNum);
        System.out.println("Remove: " + removeNum);
        System.out.println("Search: " + searchNum);
        System.out.println("Search result: " + redBlackTree.contains(searchNum));
        System.out.println("Count: " + redBlackTree.getCount());
//        System.out.println("Height: " + binaryTree.getHeight());
        redBlackTree.logTree();
    }
}