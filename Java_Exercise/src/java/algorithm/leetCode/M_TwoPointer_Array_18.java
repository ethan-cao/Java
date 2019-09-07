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
        List<List<Integer>> lists = fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        lists.forEach(System.out::println);
        System.out.println(System.lineSeparator());

        lists = fourSum(new int[]{0, 0, 0, 0}, 0);
        lists.forEach(System.out::println); // [[0,0,0,0]]
        System.out.println(System.lineSeparator());

        lists = fourSum(new int[]{0, 1, 5, 0, 1, 5, 5, -4}, 11);
        lists.forEach(System.out::println); // [[-4,5,5,5],[0,1,5,5]]
        System.out.println(System.lineSeparator());

        lists = fourSum(new int[]{-1, 0, 1, 2, -1, -4}, -1);
        lists.forEach(System.out::println); // [[-4,0,1,2],[-1,-1,0,1]]
        System.out.println(System.lineSeparator());
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();

        if (nums == null || nums.length < 4) {
            return quadruplets;
        }

        Arrays.sort(nums);

        for (int a = 0; a < nums.length - 3; ++a) {

            // skip same value number
            if (a > 0 && nums[a] == nums[a - 1]) continue;

            // if the smallest sum is larger than target, not possible
            if (nums[a] * 4 > target) break;

            // if the largest sum is smaller than target, try next one
            if (nums[a] + nums[nums.length - 1] * 3 < target) continue;

            for (int b = a + 1; b < nums.length - 2; ++b) {

                if (b > a + 1 && nums[b] == nums[b - 1]) continue; // !!! b > a+ 1, do it at least when b is a + 1

                if (nums[a] + nums[b] * 3 > target) break;

                if (nums[a] + nums[b] + nums[nums.length - 1] * 2 < target) continue;

                int c = b + 1;
                int d = nums.length - 1;

                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];

                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));

                        // try next different c
                        while (c < d && nums[c] == nums[c + 1]) c++;
                        c++;

                        // try next different d
                        while (c < d && nums[d] == nums[d - 1]) d--;
                        d--;
                    } else if (sum < target) {
                        c++;
                    } else {
                        d--;
                    }
                }
            }
        }

        return quadruplets;
    }
}
