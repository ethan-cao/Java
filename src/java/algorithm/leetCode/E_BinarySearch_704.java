package algorithm.leetCode;

/*
Given a sorted (in ascending order) integer array nums of n elements and a target value,
write a function to search target in nums.
If target exists, then return its index, otherwise return -1.

You may assume that all elements in nums are unique.
n will be in the range [1, 10000].
The value of each element in nums will be in the range [-9999, 9999].

### Example
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4

Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1

*/

public class E_BinarySearch_704 {

    public static void main(String[] args) {
        System.out.println(new int[]{-1, 0, 3, 5, 9, 12});  // 0
    }

    public static int search(int[] nums, int target) {
        return getTargetIndex(nums, 0, nums.length - 1, target);
    }

    private static int getTargetIndex(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            }
        }

        return -1;
    }

}
