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

import java.util.*;

public class M_DFS_1339 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // DFS, 7ms
    public static int maxProduct(TreeNode root) {
        final int MOD = (int) 1e9 + 7;

        // Alternatively, create mutable Long class to just contain the value
        long[] maxProduct = new long[]{0};

        long totalSum = getSum(root);

        getMaxProduct(root, totalSum, maxProduct);

        return (int) (maxProduct[0] % MOD);
    }

    private static long getSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return node.val + getSum(node.left) + getSum(node.right);
    }

    private static long getMaxProduct(TreeNode node, long totalSum, long[] maxProduct) {
        if (node == null) {
            return 0;
        }

        long leftSum = getMaxProduct(node.left, totalSum, maxProduct);
        long rightSum = getMaxProduct(node.right, totalSum, maxProduct);
        long sum = node.val + leftSum + rightSum;

        maxProduct[0] = Math.max(maxProduct[0], (totalSum - sum) * sum);

        return sum;
    }


    // DFS, 21ms
    public static int maxProduct1(TreeNode root) {
        final int MOD = (int) 1e9 + 7;

        Set<Long> sums = new HashSet<>();
        long maxProduct = 0;

        long totalSum = getSum(root, sums);

        for (long sum : sums) {
            maxProduct = Math.max(maxProduct, sum * (totalSum - sum));
        }

        return (int) (maxProduct % MOD);
    }

    private static long getSum(TreeNode node, Set<Long> sums) {
        if (node == null) {
            return 0;
        }

        long sum = node.val + getSum(node.left, sums) + getSum(node.right, sums);

        sums.add(sum);

        return sum;
    }

}
