package algorithm.leetCode;

/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0 ?
Find all unique triplets in the array which gives the sum of zero.

The solution set must not contain duplicate triplets.

### Example
[-1, 0, 1, 2, -1, -4]
A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_TwoPointer_Array_15 {

    public static void main(String... args) {
//        int[] data = {-1, 0, 1, 2, -1, -4};
        int[] data = {0, 0, 0};


        List<List<Integer>> solutions = threeSum(data);

        for (List<Integer> solution : solutions) {
            solution.forEach(System.out::print);
            System.out.println();
        }
    }

    // O(n^2)
    public static List<List<Integer>> threeSum(int[] nums) {
        final int TARGET = 0;

        Arrays.sort(nums); // important to make the inner lookup linear

        List<List<Integer>> solutions = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; ++i) {

            // skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int required = TARGET - nums[i];

            // since nums is sorted, the lookup is O(n)
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum > required) {
                    right--;
//                    right = getNextDistinctValue(nums, right, -1); // slower
                } else if (sum < required) {
                    left++;
//                    left = getNextDistinctValue(nums, left, 1);
                } else {
                    solutions.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    left = getNextDistinctValue(nums, left, 1);
                    right = getNextDistinctValue(nums, right, -1);
                }
            }
        }

        return solutions;
    }

    // skip duplicates
    private static int getNextDistinctValue(int[] nums, int value, int change) {
        while (value + change >= 0 && value + change < nums.length && nums[value] == nums[value + change]) {
            value = value + change;
        }
        value = value + change;

        return value;
    }
}