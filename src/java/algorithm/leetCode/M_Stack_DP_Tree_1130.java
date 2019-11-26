package algorithm.leetCode;

/*
Given an array arr of positive integers, consider all binary trees such that:
* Each node has either 0 or 2 children;  (full tree)
* The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
* Value of each non-leaf node = the product of the largest leaf value in its left and right subtree respectively.

Among all possible binary trees, return the smallest possible sum of the values of each non-leaf node.

It is guaranteed this sum fits into a 32-bit integer.
2 <= arr.length <= 40
1 <= arr[i] <= 15

### Example
[6,2,4] -> 32
Explanation: There are two possible trees. The 1st has non-leaf node sum 36, and the 2nd has non-leaf node sum 32.
    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4

*/

import java.util.*;

public class M_DP_Greedy_Stack_Tree_1130 {

    public static void main(String... args) {
        System.out.println(mctFromLeafValues2(new int[]{6, 2, 4}));        // 32
        System.out.println(mctFromLeafValues2(new int[]{6, 2, 4, 3}));     // 44
        System.out.println(mctFromLeafValues2(new int[]{4, 5, 1, 2, 9}));  // 77
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // DP
    // Time O(N^3), Space: O(N^2)
    public static int mctFromLeafValues(int[] arr) {
        //maxValues[i][j]: max value between arr[i] and arr[j]
        int[][] maxValues = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; ++i) {
            for (int j = i; j < arr.length; ++j) {

                if (i == j) {
                    maxValues[i][j] = arr[i];
                } else {
                    maxValues[i][j] = Math.max(maxValues[i][j - 1], arr[j]);
                }
            }
        }

        // smallestSums[i][j]: smallest sum for node that has leaf arr[i] ... arr[j]
        int[][] smallestSums = new int[arr.length][arr.length];

        // !!! pay attention to the loop construction
        // draw tree to analyze: https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/340033/C%2B%2B-with-comments
        // since the tree is build from bottom to top, we need to start calculating with length from 1
        for (int distance = 1; distance < arr.length; ++distance) {
            for (int i = 0; i + distance < arr.length; ++i) {
                int j = i + distance;

                if (distance == 1) {
                    smallestSums[i][j] = arr[i] * arr[j];
                } else {
                    // when distance > 1, partition the tree with k ∈ [i, j)
                    // value of the node = maxValues[i][k] * maxValues[i+1][j]
                    // left tree smallest sum is smallestSums[i][k], right tree smallest sum is smallestSums[k+1][j]
                    smallestSums[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; ++k) {
                        int sum = maxValues[i][k] * maxValues[k + 1][j] + smallestSums[i][k] + smallestSums[k + 1][j];
                        smallestSums[i][j] = Math.min(smallestSums[i][j], sum);
                    }
                }
            }
        }

        return smallestSums[0][arr.length - 1];
    }

    // Greedy
    // Time: O(N^2)
    public static int mctFromLeafValues1(int[] arr) {
        int smallestSum = 0;

        // LinkedList is fast for remove elements
        List<Integer> values = new LinkedList<>();
        for (int a : arr) values.add(a);

        // Basically, we need to get smallest product as much as we can
        // production requires 2 number, so use size > 1 as condition
        while (values.size() > 1) {

            // find the minimal value
            int minIdx = 0;
            for (int i = 1; i < values.size(); i++) {
                if (values.get(i) < values.get(minIdx)) {
                    minIdx = i;
                }
            }

            // pick the smaller neighbors to multiple with the minimal value and accumulate them
            int leftIdx = minIdx - 1;
            int rightIdx = minIdx + 1;

            if (leftIdx < 0) {
                smallestSum += values.get(minIdx) * values.get(rightIdx);
            } else if (rightIdx >= values.size()) {
                smallestSum += values.get(minIdx) * values.get(leftIdx);
            } else {
                smallestSum += values.get(minIdx) * Math.min(values.get(leftIdx), values.get(rightIdx));
            }

            // remove the minimal value, since it will not be used in production any more
            values.remove(minIdx);
        }

        return smallestSum;
    }

    // Stack, similar to 503
    // Time: O(N), Space: O(N)
    // find the next greater element in the array, on the left and on the right
    public static int mctFromLeafValues2(int[] arr) {
        int smallestSum = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(Integer.MAX_VALUE);

        for (int value : arr) {

            while (value >= stack.peekFirst()) {
                // value >= minValue
                int minValue = stack.pop();
                // to remove minValue, use the smaller greater value from itsleft or right
                smallestSum += minValue * Math.min(stack.peekFirst(), value);
            }

            // remove the minimal value, since it will not be used in production any more
            stack.push(value);
        }

        while (stack.size() > 2) {
            smallestSum += stack.pop() * stack.peek();
        }

        return smallestSum;
    }

}