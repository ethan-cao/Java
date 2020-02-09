package algorithm.leetCode;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
The array may contain duplicates
(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
You are given a target value to search. If found in the array return true, otherwise return false.

### Example
nums = [2,5,6,0,0,1,2], target = 0 -> true

nums = [2,5,6,0,0,1,2], target = 3 -> false

Related : 33

*/

public class M_Array_81 {

    public static void main(String... args) {
        System.out.println(search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0)); // T
        System.out.println(search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3)); // F
    }

    // binary search
    // Time: O(logN)
    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] == target) {
                return true;
            }

            if (nums[middle] > nums[left]) {
                // the left part is sorted
                if (target >= nums[left] && target <= nums[middle]) {
                    // target is in sorted part
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else if (nums[middle] < nums[left]) {
                // the right part is sorted
                if (target >= nums[middle] && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            } else {
                // for duplicates, just ignore
                // worst case time O(n), which makes the solution O(n)
                left++;
            }
        }

        return false;
    }

    // this is general solution for both 33 and 81
    public static boolean search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // skip duplicates
            while (nums[left] == nums[right] && left < right) {
                left++;
            }

            int middle = left + (right - left) / 2;

            if (target == nums[middle]) {
                return true;
            }

            if (nums[middle] >= nums[left]) {
                // the left part is sorted
                if (target >= nums[left] && target < nums[middle]) {
                    // target is in sorted part
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                // the right part is sorted
                if (target <= nums[right] && target > nums[middle]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }

        return false;
    }

}