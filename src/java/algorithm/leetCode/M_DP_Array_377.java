package algorithm.leetCode;

/*
Given an integer array with all positive numbers and no duplicates
find the number of possible combinations that add up to a positive integer target.

### Example
nums = [1, 2, 5], target = 5  ->  9
nums = [1, 2, 3], target = 4  ->  7
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

### Keys
* This question actually ask for permutation, the order matters
* you can reuse each number
* 377 is related to 518, the only difference is 518 is asking for combination, where order does not matter

*/

import java.util.Arrays;

public class M_DP_Array_377 {

    public static void main(String... args) {
        System.out.println(combinationSum4_1(new int[] { 1, 2, 3 }, 4)); // 7
        System.out.println(combinationSum4_1(new int[] { 1, 2, 3 }, 1)); // 1
        System.out.println(combinationSum4_1(new int[] { 1, 2, 3 }, 2)); // 2
        System.out.println(combinationSum4_1(new int[] { 1, 2, 3 }, 3)); // 4
        System.out.println(combinationSum4_1(new int[] { 1, 2, 3 }, 5)); // 13
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, Bottom-up
    // Time: O(N^3) 6ms
    // different from unbounded knapsack
    public static int combinationSum4_4(int[] nums, int target) {
        int L = nums.length;
        int[][] counts = new int[L][target + 1];

        for (int idx = 0; idx < L; ++idx) {
            int num = nums[idx];

            // BASE
            // permutations(i, 0) = 1
            counts[idx][0] = 1;

            // TRANSFORM
            for (int value = 1; value <= target; ++value) {

                if (num > value) {
                    // skip
                    counts[idx][value] = idx - 1 >= 0 ? counts[idx - 1][value] : 0;
                } else {
                    // take: use tree to analyze
                    // if possible to use this num, to consider all permutation among nums[0...idx], 
                    // we start using each value again, from 0 to idx
                    // in other words, count how many permutation to get (value - nums[i]) from 0 to idx
                    // that's why it's counts[idx][value - nums[i]]
                    // then sum them up
                    for (int i = 0; i <= idx; ++i) {
                        if (nums[i] <= value) {
                            counts[idx][value] += counts[idx][value - nums[i]];
                        }
                    }
                }
            }
        }

        return counts[L - 1][target];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative,
    // Time: O(N^2) 1ms
    public static int combinationSum4_1(int[] nums, int target) {
        int[] counts = new int[target + 1];

        // BASE
        counts[0] = 1;

        // TRANSFORM
        // permutation(target) = Σ permutation(target - nums[i]), nums[i] <= target
        for (int currentTarget = 1; currentTarget <= target; ++currentTarget) {
            for (int num : nums) {
                if (num <= currentTarget) {
                    counts[currentTarget] += counts[currentTarget - num];
                }
            }
        }

        return counts[target];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP
    // Time: O(N^3) 
    public int combinationSum4(int[] nums, int target) {
        int L = nums.length;

        int[][] counts = new int[target + 1][L];
        Arrays.fill(counts[0], 1);

        for (int value = 1; value <= target; ++value) {
            for (int i = 0; i < L; ++i) {
                int num = nums[i];

                if (value >= num) {
                    for (int j = 0; j <= i; ++j) {
                        if (value >= nums[j]) {
                            counts[value][i] += counts[value - nums[j]][i];
                        }
                    }
                } else {
                    counts[value][i] = i - 1 >= 0 ? counts[value][i - 1] : 0;
                }
            }
        }

        return counts[target][L - 1];
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

        // !!! permutation, check every num again，to count same elements in different
        // order
        // TRANSFORM
        // permutations(i) = Σ permutation(target - nums[i]), nums[i] <= target
        for (int num : nums) {
            if (num <= target) {
                memo[target] += count(nums, target - num, memo);
            }
        }

        return memo[target];
    }

}
