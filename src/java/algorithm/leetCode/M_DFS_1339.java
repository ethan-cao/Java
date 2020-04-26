package algorithm.leetCode;

/*
Given a binary tree root.
Split the binary tree into 2 subtrees by removing 1 edge such that the product of the sums of the subtrees are maximized

Since the answer may be too large, return it modulo 10^9 + 7.

Each tree has at most 50000 nodes and at least 2 nodes.
Each node's value is between [1, 10000].

### Example
https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/

[1,2,3,4,5,6] -> 110
[1,null,2,3,4,null,null,5,6] -> 90
[2,3,9,10,7,8,6,5,4,11,1] -> 1025
[1,1] -> 1

*/

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class M_DFS_1339 {

    public static void main(String[] args) {
//        System.out.println(maxProduct(new int[]{1, 1, 2, 2, 2})); // true
//        System.out.println(maxProduct(new int[]{3, 3, 3, 3, 4})); // false
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // DFS, ms
    public static int maxProduct(TreeNode root) {
        final int MOD = (int) 1e9 + 7;

        Set<Long> sums = new HashSet<>();

        int sum = getSum(root, sums);

        return 1;
    }

    private static int getSum(TreeNode node, Set<Long> sums) {
        if (node == null) {
            return 0;
        }

        int sum = node.val + getSum(node.left, sums) + getSum(node.right, sums);

        return 1;
    }


}