package algorithm.leetCode;

/*
Shifted Array Search

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

Review : 1

*/

public class M_Array_33 {

    public static void main(String... args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // -1
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4)); // 0
        System.out.println(search(new int[]{5, 1, 3}, 5));             // 0
    }

    // binary search
    // Time: O(logN)
    public static int search(int[] nums, int target) {
        int targetIdx = -1;

        if (nums == null || nums.length == 0) {
            return targetIdx;
        }

        int turningIdx = getTurningIdx(nums); // O(logN)
        int left = target > nums[nums.length - 1] ? 0 : turningIdx;
        int right = target > nums[nums.length - 1] ? turningIdx - 1 : nums.length - 1;

        // find target, binary search, O(logN)
        targetIdx = getTargetIdx(left, right, nums, target);

        return targetIdx;
    }

    private static int getTargetIdx(int left, int right, int[] nums, int target) {
        int targetIdx = -1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                targetIdx = middle;
                break;
            }

            // alternatively, this find the left most value that is identical as target
//            if (nums[middle] < target) {
//                left = middle + 1;
//            } else {
//                right = middle;
//            }
        }

        return targetIdx;
    }

    // find sorted array's first idx
    private static int getTurningIdx(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {  // cannot use <=, we are looking for when left== right
            int middle = left + (right - left) / 2;

            // in sorted array, nums[middle] > nums[left], nums[middle] < nums[right]
            // if nums[middle] > nums[right], turningIdx is on the right half
            if (nums[middle] > nums[right]) {
                left = middle + 1;  // nums[middle] is not possible to be the turningIdx
            } else {
                // in this case, nums[middle] <= nums[right], sorted from middle til right
                right = middle;  // cannot use middle - 1, nums[middle] could be the turningIdx
            }
        }

        // now left == right, which is the index for the smallest one
        return left;
    }

    // check common solution algorithm.leetCode.M_Array_81.search1

    // https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14435/Clever-idea-making-it-simple
    public static int search1(int[] nums, int target) {
        return 1;
    }

}