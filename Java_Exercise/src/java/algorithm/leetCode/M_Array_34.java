package algorithm.leetCode;

/*
Given an array of integers nums sorted in ascending order,
find the starting and ending position of a given target value.

runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1]

### Example
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

*/


import java.util.Arrays;

public class M_Array_34 {

    public static void main(String... args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8))); //[3,4]
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6))); //[-1,-1]
    }

    // brute force
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        return result;
    }

}