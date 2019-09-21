package algorithm.leetCode;

/*
Given an integer array with all positive numbers and no duplicates,
find the number of possible combinations that add up to a positive integer target.

### Example
nums = [1, 2, 3], target = 4
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
Therefore the output is 7.



Related : 322

*/


import java.util.Arrays;

public class M_DP_Array_377 {

    public static void main(String... args) {
        int[] nums1 = {1, 2, 3};
        System.out.println(combinationSum4_2(nums1, 1)); // 1
        System.out.println(combinationSum4_2(nums1, 2)); // 2
        System.out.println(combinationSum4_2(nums1, 3)); // 4
        System.out.println(combinationSum4_2(nums1, 4)); // 7
        System.out.println(combinationSum4_2(nums1, 5)); // 13

    }

    // Sub problem : how many ways to sum up first n number to m
    // Bottom up, use tabulation to analyze and get this approach
    public static int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        // solutions[i][j] : how many ways to sum up to i using first j items
        int[][] solutions = new int[target + 1][nums.length];
        Arrays.fill(solutions[0], 1);

        for (int i = 1; i <= target; ++i) {
            for (int j = 0; j < nums.length; ++j) {

                if (i >= nums[j]) {
                    for (int k = j; k >= 0; --k) {
                        if (i >= nums[k]) {
                            solutions[i][j] += solutions[i - nums[k]][j];
                        }
                    }
                } else {
                    solutions[i][j] = j >= 1 ? solutions[i][j - 1] : 0;
                }
            }
        }

        return solutions[target][nums.length - 1];
    }

    // optimization for combinationSum4
    public static int combinationSum4_1(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        // when calculate solution, it concerns only the same amount of items that can add up to the target
        int[] solutions = new int[target + 1];
        solutions[0] = 1;

        for (int i = 1; i <= target; ++i) {
            for (int j = 0; j < nums.length; ++j) {

                if (i >= nums[j]) {
                    solutions[i] += solutions[i - nums[j]];
                }
            }
        }

        return solutions[target];
    }

    // Recursion, top-down, DFS, use tree to analyze
    // Too slow
    public static int combinationSum4_2(int[] nums, int target) {
        return getSolution(nums, target);
    }

    private static int getSolution(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }

        int solution = 0;

        for (int i : nums) {
            if (target >= i) {
                solution += getSolution(nums, target-i);
            }
        }

        return solution;
    }

    // Recursion, top-down with cache
    public static int combinationSum4_3(int[] nums, int target) {
        return 1;
    }
}