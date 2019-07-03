package algorithm.leetCode;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.

assume no duplicate exists in the array.
time complexity <= O(log n)


### Example
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

*/


public class M_Array_33 {

    public static void main(String... args) {
        int[] data1 = {4, 5, 6, 7, 0, 1, 2};

        System.out.println(search(data1, 0)); // 4
        System.out.println(search(data1, 3)); // -1
        System.out.println(search(data1, 4)); // 0
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // locate the smallest one, binary search O(logN)
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] > nums[right]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        // now left == right, which is the index for the smallest one

        int pivot = left;

        left = target > nums[nums.length - 1] ? 0 : pivot;
        right = target > nums[nums.length - 1] ? pivot - 1 : nums.length - 1;
        int targetIndex = -1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                targetIndex = middle;
                break;
            }
        }

        return targetIndex;

    }

}