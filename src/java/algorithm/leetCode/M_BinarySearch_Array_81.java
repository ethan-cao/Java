package algorithm.leetCode;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
The array may contain duplicates
(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
You are given a target value to search. If found in the array return true, otherwise return false.

### Example
nums = [2,5,6,0,0,1,2], target = 0 -> true
nums = [2,5,6,0,0,1,2], target = 3 -> false

*/

public class M_BinarySearch_Array_81 {

    public static void main(String... args) {
        System.out.println(search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0)); // T
        System.out.println(search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3)); // F
    }

    // 0ms
    public static boolean search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            // dismiss duplicates on both sides, turn it to Q33
            while (nums[left] == nums[right] && left < right) {
                left++;
            }

            int middle = left + (right - left) / 2;

            if (nums[middle] == target) {
                return true;
            }

            if (nums[left] <= nums[middle]) {
                if (target >= nums[left] && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                if (target > nums[middle] && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }

        return false;
    }

    // binary search, same as M_Array_33.search
    // Time: O(logN), 0ms
    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] == target) {
                return true;
            }

            // since there is duplicate, we might have nums[left] = nums[middle] = nums[right]
            // use strict compare to move left and right

            if (nums[left] < nums[middle]) {
                // the left part is sorted
                if (nums[left] <= target && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else if (nums[left] > nums[middle]) {
                // the right part is sorted
                if (nums[middle] < target && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            } else {
                // for duplicates, like [1,3,1,1,1], nums[left] == nums[middle]
                // worst case time O(n), which makes the solution O(n)
                left++;
            }

            // it is also possible to compare with right
//            if (nums[middle] < nums[right]) {
//                if (target > nums[middle] && target <= nums[right]) {
//                    left = middle + 1;
//                } else {
//                    right = middle - 1;
//                }
//            } else if (nums[middle] > nums[right]) {
//                if (target >= nums[left] && target < nums[middle]) {
//                    right = middle - 1;
//                } else {
//                    left = middle + 1;
//                }
//            } else {
//                right--;
//            }
        }

        return false;
    }


}