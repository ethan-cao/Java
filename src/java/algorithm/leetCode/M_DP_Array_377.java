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
Note that different sequences are counted as different combinations.

### Keys
* This question actually ask for permutation, the order matters
* you can reuse each number
* 377 is related to 518, the only difference is 518 is asking for combination,  where order does not matter

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
    // DP, Bottom-up
    // Time: O(N^3)  6ms
    // different from unbounded knapsack
    public static int combinationSum4_4(int[] nums, int target) {
        final int L = nums.length;
        int[][] counts = new int[L][target + 1];

        // BASE
        // permutations(i, 0) = 1
        for (int i = 0; i < L; ++i) {
            counts[i][0] = 1;
        }

        // TRANSFORM
        // permutations(i, target) = Σ permutation(i, target - nums[i]), nums[i] <= target
        // permutations(i, target) = permutation(i - 1, target), nums[i] > target
        for (int i = 0; i < L; ++i) {
            for (int value = 1; value <= target; ++value) {

                // if nums[i] could be in the permutation
                if (nums[i] <= value) {

                    // look for permutations which sum up to (currentTarget - nums[i])
                    // !!! start count from 0 again to check for all possible permutation
                    for (int idx = 0; idx <= i; ++idx) {
                        counts[i][value] += value - nums[idx] >= 0 ? counts[i][value - nums[idx]] : 0;
                    }

                } else {
                    counts[i][value] = i - 1 >= 0 ? counts[i - 1][value] : 0;
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

}
