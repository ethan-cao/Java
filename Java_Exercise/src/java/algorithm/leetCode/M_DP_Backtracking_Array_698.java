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

import java.util.Arrays;

public class M_DP_Backtracking_Array_698 {

    public static void main(String... args) {
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4)); // T
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 5)); // F
        System.out.println(canPartitionKSubsets(new int[]{2, 2, 2, 2, 3, 4, 5}, 5)); // F
    }

    // Sub problem
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }

        int subsetSum = sum / k;

        // now we need to find n elements from nums[], which can add up to subsetSum


        return true;
    }

    // DP, Bit Masking
    public static boolean canPartitionKSubsets1(int[] nums, int k) {
        return true;
    }
}
