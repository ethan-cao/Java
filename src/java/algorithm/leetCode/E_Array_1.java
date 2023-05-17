package algorithm.leetCode;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].

*/

import java.util.*;

public class E_Array_1 {

    public static void main(String[] args) {
        int[] result = twoSum2(new int[] { 2, 7, 11, 15 }, 9);
        assert result.length == 2;
        assert Arrays.stream(result).anyMatch(x -> x == 3);
        assert Arrays.stream(result).anyMatch(x -> x == 1);

        System.out.println(Arrays.toString(twoSum2(new int[] { 2, 7, 11, 15 }, 9))); // 1, 0
        System.out.println(Arrays.toString(twoSum2(new int[] { 3, 2, 4 }, 6))); // 2, 1;
        System.out.println(Arrays.toString(twoSum2(new int[] { 3, 3, }, 6))); // 1, 0
    }

    // HashMap
    // Time: O(N), Space: O(N)
    public static int[] twoSum1(int[] nums, int target) {
        int[] indices = new int[2];

        // number -> its index
        Map<Integer, Integer> locationRecorder = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            int requiredNum = target - num;

            // first check the required number is in existing locationRecorder
            if (locationRecorder.containsKey(requiredNum)) {
                indices[0] = i;
                indices[1] = locationRecorder.get(requiredNum);
                break;
            }

            // if the required number is not there, record it
            // this must happen in the end, otherwise, the latter duplicate value overrides index, e.g.{3,3} 6
            locationRecorder.put(num, i);
        }

        return indices;
    }

    // Time: O(N), Space: O(N)
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];

        // HashMap
        // number -> its counterpart's index (number + counterpart = target)
        Map<Integer, Integer> locationRecorder = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            int requiredNum = target - num;

            if (locationRecorder.containsKey(num)) {
                result[0] = i;
                result[1] = locationRecorder.get(num);
                break;
            }

            locationRecorder.put(requiredNum, i);
        }

        return result;
    }

}