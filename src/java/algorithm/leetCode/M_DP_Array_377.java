package algorithm.leetCode;

/*
Given an integer array with all positive numbers and no duplicates
find the number of possible combinations that add up to a positive integer target.

### Example
nums = [1, 2, 3], target = 4  ->  7
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.

### Keys
* This question actually ask for permutation, the order matters
* you can reuse each number
* 377 is similar to 518, but they use quite different DP approaches

*/

import java.util.Arrays;

public class M_DP_Array_377 {

    public static void main(String... args) {
        System.out.println(combinationSum4_1(new int[]{1, 2, 3}, 4));  // 7
        System.out.println(combinationSum4_1(new int[]{1, 2, 3}, 1));  // 1
        System.out.println(combinationSum4_1(new int[]{1, 2, 3}, 2));  // 2
        System.out.println(combinationSum4_1(new int[]{1, 2, 3}, 3));  // 4
        System.out.println(combinationSum4_1(new int[]{1, 2, 3}, 5));  // 13
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP recursive with cache, brute force
    // Time: O(N*target), 0ms, Space: O(target)
    public static int combinationSum4_3(int[] nums, int target) {
        Integer[] memo = new Integer[target + 1];
        return count(nums, target, memo);
    }

    private static int count(int[] nums, int target, Integer[] memo) {
        // BASE
        // permutations(0) = 1
        if (target == 0) {
            return 1;
        }

        if (memo[target] != null) {
            return memo[target];
        }

        memo[target] = 0;

        // !!! permutation, check every num again，to count same elements in different order
        // TRANSFORM
        // permutations(i) = Σ permutation(target - nums[i]), nums[i] <= target
        for (int num : nums) {
            if (num <= target) {
                memo[target] += count(nums, target - num, memo);
            }
        }

        return memo[target];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative, 
    // derived from DP recursive with cache or draw table, analyze from combinationSum4_5
    // Time: O(N^2) 1ms
    public static int combinationSum4_1(int[] nums, int target) {
        int[] permutations = new int[target + 1];

        // BASE
        permutations[0] = 1;

        // TRANSFORM
        // permutation(target) = Σ permutation(target - nums[i]), nums[i] <= target
        for (int currentTarget = 1; currentTarget <= target; ++currentTarget) {
            for (int num : nums) {
                if (num <= currentTarget) {
                    permutations[currentTarget] += permutations[currentTarget - num];
                }
            }
        }

        return permutations[target];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, Bottom-up
    // Time: O(N^3) 6ms
    public static int combinationSum4_5(int[] nums, int target) {
        int[][] results = new int[target + 1][nums.length];
        Arrays.fill(results[0], 1);

        for (int currentTarget = 1; currentTarget <= target; ++currentTarget) {
            for (int idx = 0; idx < nums.length; ++idx) {
                int num = nums[idx];

                if (num <= currentTarget) {

                    for (int i = 0; i <= idx; ++i) {
                        if (nums[i] <= currentTarget) {
                            results[currentTarget][idx] += results[currentTarget - nums[i]][idx];
                        }
                    }

                } else {
                    results[currentTarget][idx] = idx - 1 >= 0 ? results[currentTarget][idx - 1] : 0;
                }
            }
        }

        return results[target][nums.length - 1];
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, Bottom-up
    // different from unbounded knapsack
    // Time: O(N^3)  6ms
    public static int combinationSum4_4(int[] nums, int target) {
        final int L = nums.length;
        int[][] permutations = new int[L][target + 1];

        // BASE
        // permutations(i, 0) = 1
        for (int i = 0; i < L; ++i) {
            permutations[i][0] = 1;

        }

        // TRANSFORM
        // permutations(i, target) = Σ permutation(i, target - nums[i]), nums[i] <= target
        // permutations(i, target) = permutation(i - 1, target), nums[i] > target
        for (int i = 0; i < L; ++i) {
            for (int currentTarget = 1; currentTarget <= target; ++currentTarget) {

                // if nums[i] could be in the permutation
                if (nums[i] <= currentTarget) {

                    // look for permutations which sum up to (currentTarget - nums[i])
                    // !!! start count from 0 again to check for all possible permutation
                    for (int idx = 0; idx <= i; ++idx) {
                        permutations[i][currentTarget] += currentTarget - nums[idx] >= 0 ? permutations[i][currentTarget - nums[idx]] : 0;
                    }

                } else {
                    permutations[i][currentTarget] = i - 1 >= 0 ? permutations[i - 1][currentTarget] : 0;
                }
            }
        }

        return permutations[L - 1][target];
    }

}
