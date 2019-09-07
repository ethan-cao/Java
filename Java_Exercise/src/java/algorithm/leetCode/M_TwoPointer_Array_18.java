package algorithm.leetCode;

/*
Given an array nums of n integers and an integer target,
are there elements a, b, c, and d in nums such that a + b + c + d = target?
Find all unique quadruplets in the array which gives the sum of target.

The solution set must not contain duplicate quadruplets.

### Example
nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_TwoPointer_Array_18 {

    public static void main(String... args) {

        for (List<Integer> list : fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0)) {
            list.forEach(System.out::println);
        }

        for (List<Integer> list : fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0)) {
            list.forEach(System.out::println);
        }

    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();

        if (nums == null || nums.length < 4) {
            return quadruplets;
        }

        Arrays.sort(nums);

        List<Integer> quadruplet = new ArrayList<>();


        if (quadruplet.size() == 4) {
            quadruplets.add(quadruplet);
        }

        return quadruplets;
    }
}
