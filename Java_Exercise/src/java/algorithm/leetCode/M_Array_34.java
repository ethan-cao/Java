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
        System.out.println(Arrays.toString(searchRange1(new int[]{5, 7, 7, 8, 8, 10}, 8))); //[3,4]
        System.out.println(Arrays.toString(searchRange1(new int[]{5, 7, 7, 8, 8, 10}, 6))); //[-1,-1]
        System.out.println(Arrays.toString(searchRange1(new int[]{1}, 1)));                 //[0,0]
        System.out.println(Arrays.toString(searchRange1(new int[]{1, 3}, 1)));              //[0,0]
        System.out.println(Arrays.toString(searchRange1(new int[]{1, 4}, 4)));              //[1,1]
        System.out.println(Arrays.toString(searchRange1(new int[]{1, 2, 3}, 1)));           //[0,0]
    }

    // O(log n), Binary search
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        if (nums == null || nums.length == 0) {
            return result;
        }

        // seek left boundary from 0 to length-1
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int middle = start + (end - start) / 2;  // biased towards left

            if (nums[middle] < target) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }

        // if we could not find left boundary, no result
        if (nums[start] != target) {
            return result;
        }

        // seek right boundary from left boundary to length-1
        end = nums.length - 1;
        while (start < end) {
            int middle = start + (end - start) / 2 + 1; // biased towards right

            if (nums[middle] > target) {
                end = middle - 1; // this is like middle + 1 if middle = start + (end - start) / 2
            } else
                start = middle;
        }

        result[0] = start;
        result[1] = end;

        return result;
    }

    public static int[] searchRange1(int[] nums, int target) {
        int[] result = {-1, -1};

        if (nums == null || nums.length == 0) {
            return result;
        }

        int start = getStart(nums, target);
        if (start == -1) return result;
        int end = getEnd(nums, target);

        result[0] = start;
        result[1] = end;

        return result;
    }

    private static int getStart(int[] nums, int target) {
        int idx = -1;
        int left = 0;
        int right = nums.length - 1;

        // !!! =, iterate all
        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (target <= nums[middle]) {
                right = middle - 1; // move towards left
            } else {
                left = middle + 1;
            }

            // update idx when found match
            idx = nums[middle] == target ? middle : idx;
        }

        return idx;
    }

    private static int getEnd(int[] nums, int target) {
        int idx = -1;
        int left = 0;
        int right = nums.length - 1;

        // !!! =, iterate all
        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (target >= nums[middle]) {
                left = middle + 1;  // move towards right
            } else {
                right = middle - 1;
            }

            // update idx when found match
            idx = nums[middle] == target ? middle : idx;
        }

        return idx;
    }

    public static int[] searchRange2(int[] nums, int target) {
        int[] result = {-1, -1};

        if (nums == null || nums.length == 0) {
            return result;
        }

        int start = findLargeEqualIdx(nums, target);
        if (nums[start] != target) return result;
        int end = findLargeEqualIdx(nums, target + 1);

        result[0] = start;
        result[1] = nums[end] >= target + 1 ? end - 1 : end;  // if can't find target+1, end is nums.length -1

        return result;
    }

    private static int findLargeEqualIdx(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int middle = left + (right - left) / 2;

            if (target > nums[middle]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }

}