package algorithm.leetCode;

/*
Given an array of integers nums sorted in ascending order,
find the starting and ending position of a given target value.

runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1]

### Example
nums = [5,7,7,8,8,10], target = 8 -> [3,4]
nums = [5,7,7,8,8,10], target = 6 -> [-1,-1]

*/

import java.util.Arrays;

public class M_BinarySearch_Array_34 {

    public static void main(String... args) {
        System.out.println(Arrays.toString(searchRange1(new int[]{5, 7, 7, 8, 8, 10}, 8))); //[3,4]
        System.out.println(Arrays.toString(searchRange1(new int[]{5, 7, 7, 8, 8, 10}, 6))); //[-1,-1]
        System.out.println(Arrays.toString(searchRange1(new int[]{1}, 1)));                 //[0,0]
        System.out.println(Arrays.toString(searchRange1(new int[]{1, 3}, 1)));              //[0,0]
        System.out.println(Arrays.toString(searchRange1(new int[]{1, 4}, 4)));              //[1,1]
        System.out.println(Arrays.toString(searchRange1(new int[]{1, 2, 3}, 1)));           //[0,0]
    }

    // BinarySearch O(NlogN), 0ms
    public static int[] searchRange1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int leftBoundary = findLeftBoundary(nums, target);

        if (leftBoundary == -1) {
            return new int[]{-1, -1};
        }

        int rightBoundary = findRightBoundary(nums, target);

        return new int[]{leftBoundary, rightBoundary};
    }

    private static int findLeftBoundary(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                // when nums[middle] == target, target exists in nums
                // since looking for left boundary, move the right boundary towards left to narrow the range
                // in the end right = left - 1, if nums[left] == target, then left is the boundary
                right = middle - 1;
            }
        }

        // as long as left is still within range and nums[left] == target, found
        if (left < nums.length && nums[left] == target) {
            return left;
        }

        return -1;
    }

    private static int findRightBoundary(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                // when nums[middle] == target, target exists in nums
                // since looking for right boundary, move the left boundary towards right to narrow the range
                // in the end left = right + 1, if nums[right] == target, then right is the boundary
                left = middle + 1;
            }
        }

        // as long as right is still in range and nums[right] == target, found
        if (right >= 0 && nums[right] == target) {
            return right;
        }

        return -1;
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