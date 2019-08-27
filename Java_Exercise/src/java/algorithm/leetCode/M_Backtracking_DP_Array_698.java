package algorithm.leetCode;

/*
Given an array of integers nums and a positive integer k,
find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.

### Example
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

This is 0-1 knapsack problem

Related : 416

*/

import java.util.stream.IntStream;

public class M_Backtracking_DP_Array_698 {

    public static void main(String... args) {
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4)); // T
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 5)); // F
        System.out.println(canPartitionKSubsets(new int[]{2, 2, 2, 2, 3, 4, 5}, 5)); // F
    }

    // Backtracking, DFS
    // upper bound time complexity: O(k*2^n)
    // it takes the inner recursion 2^n time to find a good subset. Once the 1st subset is found,
    // we go on to find the second, which would take 2^n roughly (because some numbers have been marked as visited).
    // So T = 2^n + 2^n + 2^n + ... = k * 2^n.
    // Space complexity : O(n)
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = IntStream.of(nums).sum();
        int targetSum = sum / k;

        if (nums.length < k || sum % k != 0) {
            return false;
        }

        return canPartition(nums, 0, new boolean[nums.length], k, 0, targetSum);
    }

    private static boolean canPartition(int[] nums, int startIdx, boolean[] used, int k, int currentSum, int targetSum) {
        // if there is only 1 bucket left, since all the rest reaches target, the last one reaches targetSum for sure
        if (k == 1) {
            return true;
        }

        // if the current bucket reaches target sum, try next bucket
        if (currentSum == targetSum) {
            return canPartition(nums, 0, used, k - 1, 0, targetSum);
        }

        // optimization
        if (currentSum > targetSum) {
            return false;
        }

        // examine each used num
        for (int i = startIdx; i < nums.length; ++i) {
            if (!used[i]) {

                // try
                used[i] = true;
                if (canPartition(nums, i + 1, used, k, currentSum + nums[i], targetSum)) {
                    return true;
                }

                // if not work, backtrack
                used[i] = false;
            }
        }

        return false;
    }

    // DP, Bit Masking
    public static boolean canPartitionKSubsets1(int[] nums, int k) {
        return true;
    }
}
