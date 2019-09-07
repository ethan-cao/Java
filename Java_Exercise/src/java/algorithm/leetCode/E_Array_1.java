package algorithm.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].

// R: 1

*/

public class E_Array_1 {

    public static void main(String[] args) {
        int[] data = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum2(data, 9))); // 0, 1

        int[] data1 = {3, 2, 4};
        System.out.println(Arrays.toString(twoSum2(data1, 6))); // 1, 2

        int[] data2 = {3, 3};
        System.out.println(Arrays.toString(twoSum2(data2, 6))); // 0, 1
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        // element -> its index, use map to make lookup fast
        Map<Integer, Integer> tally = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            tally.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; ++i) {
            int requiredNum = target - nums[i];

            if (tally.containsKey(requiredNum) && i != tally.get(requiredNum)) {
                result[0] = i;
                result[1] = tally.get(requiredNum);
                break;
            }
        }

        return result;
    }

    public static int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];

        // number -> its index
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int requiredNum = target - nums[i];

            // first check the required number is in existing map
            if (map.containsKey(requiredNum)) {
                result[0] = i;
                result[1] = map.get(requiredNum);
                break;
            }

            // if the required number is not there, record it
            // this must happen in the end, otherwise, the latter duplicate value overrides index, e.g.{3,3} 6
            map.put(nums[i], i);
        }

        return result;
    }

    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];

        // number -> its counterpart's index (number + counterpart = target)
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int requiredNum = target - nums[i];

            if (map.containsKey(nums[i])) {
                result[0] = i;
                result[1] = map.get(nums[i]);
                break;
            }

            map.put(requiredNum, i);
        }

        return result;
    }
}