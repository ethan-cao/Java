package algorithm.leetCode;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
assume no duplicate exists in the array.

### Example
nums = [4,5,6,7,0,1,2], target = 0 -> 4
nums = [4,5,6,7,0,1,2], target = 3 -> -1

*/

public class M_BinarySearch_Array_33 {

    public static void main(String... args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // -1
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4)); // 0
        System.out.println(search(new int[]{5, 1, 3}, 5));             // 0
        System.out.println(search(new int[]{1, 3}, 1));                // 0
    }

    // binary search, same as M_Array_81.search
    // Time: O(NlogN), 0ms
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] == target) {
                return middle;
            }

            // !!! including equal case, since, middle is biased towards left
            if (nums[left] <= nums[middle]) {
                // the left half is sorted
                if (target >= nums[left] && target < nums[middle]) {
                // >= : possible for target == nums[left], includes this case
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                // the right half is sorted
                if (target > nums[middle] && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }

        return -1;
    }

    // Time: O(logN)
    public static int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int pivot = getPivot(nums, 0, nums.length - 1); // O(logN)

        // !!! compare with nums[nums.length -1]
        int left = target > nums[nums.length - 1] ? 0 : pivot;
        int right = target > nums[nums.length - 1] ? pivot - 1 : nums.length - 1;

        return getTargetIdx(nums, left, right, target);
    }

    // find the pivot, the sorted array's first idx
    // binary search, O(logN)
    private static int getPivot(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }

        // narrow the window until left === right
        while (left < right) {
            int middle = left + (right - left) / 2;

            // !!! cannot compare nums[middle] and nums[left],
            // when nums[middle] > nums[left], pivot could be in [left, middle] or [middle, right]

            if (nums[middle] > nums[right]) {
                left = middle + 1; // not possible for left to be middle
            } else {
                right = middle;  // possible to right to be middle
            }
        }

        // now left == right, which is the index for the smallest one
        return left;
    }

    // binary search, O(logN)
    private static int getTargetIdx(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        while (left < right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return nums[left] == target ? left : -1;
    }

    // https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14435/Clever-idea-making-it-simple
    public static int search2(int[] nums, int target) {
        return 1;
    }

}