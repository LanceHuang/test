package com.lance.test.common.balancedbinarytree;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Lance
 * @since 2021/9/22
 */
public class BalancedBinaryTreeTest {

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
        BalancedBinaryTree<Integer> binaryTree = new BalancedBinaryTree<>();
        for (int num : nums) {
            binaryTree.insert(num);
//            System.out.println("============================== " + num);
//            binaryTree.logTree();
//            System.out.println("==============================");
        }

        // 查询数据
        int searchNum = 20;
        System.out.println("Search: " + searchNum);
        System.out.println("Search result: " + binaryTree.contains(searchNum));
        System.out.println("Count: " + binaryTree.getCount());
        System.out.println("Height: " + binaryTree.getHeight());
        binaryTree.logTree();
        System.out.println();

        // 删除数据
        int removeNum = nums[2];
        binaryTree.remove(removeNum);
        System.out.println("Remove: " + removeNum);
        System.out.println("Search: " + searchNum);
        System.out.println("Search result: " + binaryTree.contains(searchNum));
        System.out.println("Count: " + binaryTree.getCount());
        System.out.println("Height: " + binaryTree.getHeight());
        binaryTree.logTree();
    }
}