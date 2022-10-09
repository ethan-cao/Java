package algorithm.leetCode;

/*
Given an array arr of positive integers, consider all binary trees such that:
  * Each node has either 0 or 2 children;  (full tree)
  * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
  * Value of each non-leaf node = the product of the largest leaf value in its left and right subtree.
Among all possible binary trees, return the smallest possible sum of the values of each non-leaf node.

It is guaranteed this sum fits into a 32-bit integer.
2 <= arr.length <= 40
1 <= arr[i] <= 15

### Example
[6,2,4] -> 32
There are 2 possible trees. The 1st has non-leaf node sum 36, and the 2nd has non-leaf node sum 32.
    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4

*/

import java.util.*;

public class M_Stack_DP_Tree_1130 {

    public static void main(String... args) {
        System.out.println(mctFromLeafValues2(new int[]{6, 2, 4})); // 32
        System.out.println(mctFromLeafValues2(new int[]{6, 2, 4, 3})); // 44
        System.out.println(mctFromLeafValues2(new int[]{4, 5, 1, 2, 9})); // 77
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Stack, Extrema, 1ms
    // Time: O(N), Space: O(N)
    public static int mctFromLeafValues1(int[] nums) {
        int smallestSum = 0;
        final int L = nums.length;

        // since we need values just besides the local minima to derive the result, use monotonic decreasing stack
        final Deque<Integer> stack = new ArrayDeque<>();

        // non-leaf_node = left_largest_leaf * right_largest_leaf
        // we need smallest_left_largest_leaf and smallest_right_largest_leaf
        // so we have the smallest sum of all non-leaf nodes
        // find the concave 凹 (convex 凸)
        for (int rightIdx = 0; rightIdx < L; ++rightIdx) {
            int rightNum = nums[rightIdx];

            while (!stack.isEmpty() && stack.peek() <= rightNum) {
                // find the concave 凹 (convex 凸)
                // middleNum is a local minima
                int middleNum = stack.pop();
                int leftNum = stack.isEmpty() ? rightNum : stack.peek();

                // middleNum is the smallest, then just find the smaller between leftNum and rightNum
                // smallest non-leaf node sum = left_largest_leaf * right_largest_leaf
                smallestSum += middleNum * Math.min(leftNum, rightNum);
            }

            stack.push(rightNum);
        }

        // now, we sum all local minima leaf, still need to go through the rest leaf

        while (stack.size() > 1) { // > 1 because we have a peek() after pop() below
            smallestSum += stack.pop() * stack.peek();
        }

        return smallestSum;
    }

    // Greedy
    // Time: O(N^2)
    public static int mctFromLeafValues3(int[] arr) {
        int smallestSum = 0;

        // LinkedList is faster to remove elements
        List<Integer> values = new LinkedList<>();
        for (int a : arr)
            values.add(a);

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

            // pick the smaller neighbors to multiple with the minimal value and accumulate
            // them
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

    // DP, iterative, kind of like brute force
    // Time O(N^3), Space: O(N^2)
    // 4ms
    public static int mctFromLeafValues2(int[] arr) {
        final int L = arr.length;

        // maxValues[i][j]: largest leaf value between [i, j]
        int[][] maxValues = new int[L][L];

        for (int i = 0; i < L; ++i) {
            for (int j = i; j < L; ++j) {

                if (i == j) {
                    maxValues[i][j] = arr[i];
                } else {
                    maxValues[i][j] = Math.max(maxValues[i][j - 1], arr[j]);
                }
            }
        }

        // smallestSums[i][j]: smallest sum for node that has leaf arr[i] ... arr[j]
        int[][] smallestSums = new int[L][L];

        // since the tree is build from bottom to top, we need to start calculating with length of 1
        for (int distance = 1; distance < L; ++distance) {
            for (int start = 0; start + distance < L; ++start) {
                int end = start + distance;

                if (distance == 1) {
                    // BASE
                    smallestSums[start][end] = arr[start] * arr[end];
                } else {
                    // when distance > 1, partition the tree to [start, i] and [i + 1, end]
                    smallestSums[start][end] = Integer.MAX_VALUE;

                    // TRANSFORM
                    // smallestSums[i][j] = smallestSums[i][k] + smallestSums[k][j] + max(arr[i, k]) * max(arr[k + 1, j])
                    for (int i = start; i < end; ++i) {
                        int nodeValue = maxValues[start][i] * maxValues[i + 1][end];
                        int leftChild = smallestSums[start][i];
                        int rightChild = smallestSums[i + 1][end];

                        int sum = leftChild + nodeValue + rightChild;
                        smallestSums[start][end] = Math.min(smallestSums[start][end], sum);
                    }
                }
            }
        }

        return smallestSums[0][L - 1];
    }

    // DP, recursive
    // 4ms
    public int mctFromLeafValues4(int[] arr) {
        final int L = arr.length;

        // maxValues[i][j]: largest leaf value between [i, j]
        int[][] maxValues = new int[L][L];

        for (int i = 0; i < L; ++i) {
            for (int j = i; j < L; ++j) {

                if (i == j) {
                    maxValues[i][j] = arr[i];
                } else {
                    maxValues[i][j] = Math.max(maxValues[i][j - 1], arr[j]);
                }
            }
        }

        Integer[][] memo = new Integer[L][L];

        return find(arr, 0, L - 1, memo, maxValues);
    }

    public int find(int[] arr, int start, int end, Integer[][] memo, int[][] maxValues) {
        if (start == end) {
            return 0;
        }

        if (memo[start][end] != null) {
            return memo[start][end];
        }

        int minimalCost = Integer.MAX_VALUE;

        for (int nodeIdx = start; nodeIdx < end; nodeIdx++) {
            int node = maxValues[start][nodeIdx] * maxValues[nodeIdx + 1][end];
            int leftChild = find(arr, start, nodeIdx, memo, maxValues);
            int rightChild = find(arr, nodeIdx + 1, end, memo, maxValues);

            minimalCost = Math.min(minimalCost, leftChild + node + rightChild);
        }

        memo[start][end] = minimalCost;

        return minimalCost;
    }
}       
