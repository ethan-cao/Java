package algorithm.leetCode;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].

*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class E_Array_1 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum2(new int[]{2, 7, 11, 15}, 9))); // 1, 0
        System.out.println(Arrays.toString(twoSum2(new int[]{3, 2, 4}, 6)));      // 2, 1
        System.out.println(Arrays.toString(twoSum2(new int[]{3, 3,}, 6)));        // 1, 0
    }

    // Time: O(NlogN), logN is to find the element or insert the element to the map
    // Space: O(N)
    public static int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];

        // number -> its index
        Map<Integer, Integer> tally = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int requiredNum = target - nums[i];

            // first check the required number is in existing tally
            if (tally.containsKey(requiredNum)) {
                result[0] = i;
                result[1] = tally.get(requiredNum);
                break;
            }

            // if the required number is not there, record it
            // this must happen in the end, otherwise, the latter duplicate value overrides index, e.g.{3,3} 6
            tally.put(nums[i], i);
        }

        return result;
    }

    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];

        // number -> its counterpart's index (number + counterpart = target)
        Map<Integer, Integer> tally = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int requiredNum = target - nums[i];

            if (tally.containsKey(nums[i])) {
                result[0] = i;
                result[1] = tally.get(nums[i]);
                break;
            }

            tally.put(requiredNum, i);
        }

        return result;
    }

}