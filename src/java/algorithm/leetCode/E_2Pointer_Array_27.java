package algorithm.leetCode;

import java.util.Arrays;

/*
Given an array nums and a value val, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

### Example:
[3,2,2,3], 3 -> 2
 with the first two elements of nums being 2.

[0,1,2,2,3,0,4,2], 2 -> 5
with the first five elements of nums containing 0, 1, 3, 0, and 4.
Note that the order of those five elements can be arbitrary.

 */
public class E_2Pointer_Array_27 {

    // Similar to Q26

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        System.out.println(removeElement(nums, 3)); // 2
        System.out.println(Arrays.toString(nums));

        int[] nums1 = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeElement(nums1, 2)); // 5
        System.out.println(Arrays.toString(nums1));
    }

    public static int removeElement(int[] nums, int val) {
        int pointer = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != val) {
                nums[pointer] = nums[i];
                pointer++;
            }
        }

        return pointer ;
    }
}
