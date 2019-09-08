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
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> solutions = threeSum(nums);
//        solutions.forEach(System.out::println); //  [[-1, 0, 1] [-1, -1, 2]]
//        System.out.println();

        Arrays.sort(nums);
        solutions = K_Sum.kSum(nums, 0, 3, 0);
        solutions.forEach(System.out::println); //  [[-1, 0, 1] [-1, -1, 2]]
        System.out.println();

        int[] nums1 = {-2, 0, 0, 2, 2};
        List<List<Integer>> solutions1 = threeSum(nums1);
//        solutions1.forEach(System.out::println); // [[-2,0,2]]
//        System.out.println();

        Arrays.sort(nums1);
        solutions1 = K_Sum.kSum(nums1, 0, 3, 0);
        solutions1.forEach(System.out::println); // [[-2,0,2]]
        System.out.println();

    }

    // O(n^2)
    public static List<List<Integer>> threeSum(int[] nums) {
        final int TARGET = 0;

        Arrays.sort(nums); // important to make the inner lookup linear

        List<List<Integer>> solutions = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; ++i) {

            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicates

            if (nums[i] * 3 > TARGET) break; // if smallest possible sum is larger than target, not possible

            if (nums[i] + nums[nums.length - 1] * 2 < TARGET) continue; // if largest possible sum is smaller than target

            // since nums is sorted, the lookup is O(n), not it is 2 sum problem
            int required = TARGET - nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == required) {
                    solutions.add(Arrays.asList(nums[i], nums[left], nums[right]));  // list view on array

                    while (left < right && nums[left] == nums[left + 1]) left++; // check next different nums[left]
                    left++;

                    while (left < right && nums[right] == nums[right - 1]) right--; // check next different nums[right]
                    right--;
                } else if (sum > required) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return solutions;
    }

}