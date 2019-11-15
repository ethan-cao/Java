package algorithm.leetCode;

/*
Given an array arr of positive integers, consider all binary trees such that:

* Each node has either 0 or 2 children;  (Full tree)
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

import java.util.ArrayDeque;
import java.util.Deque;

public class M_DP_Stack_Tree_1130 {

    public static void main(String... args) {
        System.out.println(mctFromLeafValues(new int[]{6, 2, 4}));        // 32
        System.out.println(mctFromLeafValues(new int[]{6, 2, 4, 3}));     // 44
        System.out.println(mctFromLeafValues(new int[]{4, 5, 1, 2, 9}));  // 77
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
        //max[i][j]: max value between arr[i][j]
        int[][] max = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; ++i) {
            int localMax = 0;
            for (int j = i; j < arr.length; ++j) {
                localMax = Math.max(localMax, arr[j]);
                max[i][j] = localMax;
            }
        }

        // smallestSums[i][j]: smallest sum value for node that has leaf arr[i] ... a[j]
        int[][] smallestSums = new int[arr.length][arr.length];

        for (int length = 1; length < arr.length; ++length) {
            for (int i = 0; i + length < arr.length; ++i) {
                int j = i + length;

//                max[i][j] = Math.max(max[i][j - 1], arr[j]);

                smallestSums[i][j] = Integer.MAX_VALUE;

                if (length == 1) {
                    smallestSums[i][j] = arr[i] * arr[j];
                } else {
                    // check all possible k, to find the smallest sum
                    for (int k = i; k < j; ++k) {
                        smallestSums[i][j] = Math.min(smallestSums[i][j], smallestSums[i][k] + max[i][k] * max[k + 1][j] + smallestSums[k + 1][j]);
                    }
                }
            }
        }

        return smallestSums[0][arr.length - 1];
    }

    // Stack
    public static int mctFromLeafValues1(int[] arr) {
        return 1;
    }
}