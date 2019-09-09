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
        System.out.println(Arrays.toString(searchRange(new int[]{1}, 1))); //[0,0]
        System.out.println(Arrays.toString(searchRange(new int[]{1, 3}, 1))); //[0,0]
        System.out.println(Arrays.toString(searchRange(new int[]{1, 4}, 4))); //[1,1]
        System.out.println(Arrays.toString(searchRange(new int[]{1, 2, 3}, 1))); //[0,0]
    }

    // O(log n)
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        if (nums.length == 1 && nums[0] == target) {
            return new int[]{0, 0};
        }

        int[] result = {-1, -1};

        if (nums.length == 2) {
            result[0] = nums[0] == target ? 0 : nums[1] == target ? 1 : -1;
            result[1] = nums[0] == target ? nums[1] == target ? 1 : 0 : result[0] == 1 ? 1 : -1;
            return result;
        }

        int start = 0;
        int end = nums.length - 1;
        int middle = start + (end - start) / 2;

        while (middle >= 0 && middle < nums.length && end - start > 1) {

            if (nums[middle] == target) {
                int left = middle;
                int right = middle;

                while (left - 1 >= 0 && nums[left - 1] == nums[left]) {
                    left--;
                }
                result[0] = left;

                while (right + 1 < nums.length && nums[right + 1] == nums[right]) {
                    right++;
                }
                result[1] = right;

                break;
            } else if (nums[middle] < target) {
                start = middle;
            } else {
                end = middle;
            }

            middle = start + (end - start) / 2;

        }

        return result;
    }

}