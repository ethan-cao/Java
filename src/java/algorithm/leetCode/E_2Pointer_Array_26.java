package algorithm.leetCode;

import java.util.Arrays;

/*
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

### Example:
[1,1,2] -> 2
with the first two elements of nums being 1 and 2 respectively.
It doesn't matter what you leave beyond the returned length.

[0,0,1,1,1,2,2,3,3,4] -> 5
with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

 */

public class E_2Pointer_Array_26 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(removeDuplicates(nums)); // 2
        System.out.println(Arrays.toString(nums)); // {1,2}

        int[] nums1 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums1)); // 5
        System.out.println(Arrays.toString(nums1)); // {0,1,2,3,4}
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }

        // nums is sorted

        int uniqueIndex = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (nums[uniqueIndex] != nums[i]) {
                uniqueIndex++;
                nums[uniqueIndex] = nums[i];
            }
        }

        return uniqueIndex + 1;
    }

}
