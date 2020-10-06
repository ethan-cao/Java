package algorithm.leetCode;

/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0 ?
Find all unique triplets in the array which gives the sum of zero.

The solution set must not contain duplicate triplets.

### Example
[-1, 0, 1, 2, -1, -4]
A solution set is:
[  [-1, 0, 1],
   [-1, -1, 2] ]
*/

import java.util.*;

public class M_3Pointer_Array_15 {

    public static void main(String... args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> solutions = threeSum(nums);
        solutions.forEach(System.out::println); //  [[-1, 0, 1] [-1, -1, 2]]
        System.out.println();

        int[] nums1 = {-2, 0, 0, 2, 2};
        List<List<Integer>> solutions1 = threeSum(nums1);
        solutions1.forEach(System.out::println); // [[-2,0,2]]
        System.out.println();

//        Arrays.sort(nums);
//        solutions = K_Sum.kSum(nums, 0, 3, 0);
//        solutions.forEach(System.out::println); //  [[-1, 0, 1] [-1, -1, 2]]
//        System.out.println();

//        Arrays.sort(nums1);
//        solutions1 = K_Sum.kSum(nums1, 0, 3, 0);
//        solutions1.forEach(System.out::println); // [[-2,0,2]]
//        System.out.println();
    }

    // Time: O(N^2), 19ms
    // Three Pointer, initial position: left = 0, middle = left+1, right = L -1
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> solutions = new ArrayList<>();

        Arrays.sort(nums); // important to make the inner lookup linear

        for (int left = 0; left < nums.length - 2; ++left) {
            // skip duplicates
            if (left > 0 && nums[left] == nums[left - 1]) {
                continue;
            }

            // if smallest possible sum is larger than target, not possible
            if (nums[left] * 3 > 0) {
                break;
            }

            // if largest possible sum is smaller than target, try next one
            if (nums[left] + nums[nums.length - 1] * 2 < 0) {
                continue;
            }

            for (int middle = left + 1, right = nums.length - 1; middle < right; ) {
                int sum = nums[left] + nums[middle] + nums[right];

                if (sum == 0) {
                    solutions.add(Arrays.asList(nums[left], nums[middle], nums[right]));  // list view on array, fixed size

                    // move either middle or right to get a different combination

//                    while (middle < nums.length - 1 && nums[middle] == nums[middle + 1]) middle++;
//                    middle++;

                    while (right > 1 && nums[right] == nums[right - 1]) right--;
                    right--;
                } else if (sum < 0) {
                    // skip duplicates, assures the next combination is different
                    while (middle + 1 < nums.length - 1 && nums[middle] == nums[middle + 1]) middle ++;
                    middle++;
                } else {
                    // skip duplicates, assures the next combination is different
                    while (right - 1 > middle && nums[right] == nums[right-1]) right--;
                    right--;
                }
            }
        }

        return solutions;
    }

}