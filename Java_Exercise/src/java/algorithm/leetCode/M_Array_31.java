package algorithm.leetCode;

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers

If such arrangement is not possible, it must rearrange it as the lowest possible order
(ie, sorted in ascending order).
The replacement must be in-place and use only constant extra memory.

### Example
1,2,3 → 1,3,2
for 1, 2 and 3, there are P(3,3) = 6 permutations : 123, 132, 213, 231, 312, 321
the given sequence is 123, the next lexicographically greater permutation is 132

3,2,1 → 1,2,3
1,1,5 → 1,5,1

*/

import java.util.Arrays;

public class M_Array_31 {

    public static void main(String... args) {
        int[] nums = {1, 2, 3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // 1 3 2

//        int[] nums1 = {3, 2, 1};
//        nextPermutation(nums1);
//        System.out.println(Arrays.toString(nums1)); // 1 2 3

        int[] nums2 = {1, 1, 5};
//        nextPermutation(nums2);
//        System.out.println(Arrays.toString(nums2)); // 1 5 1

        int[] nums3 = {1, 4, 6, 66, 23, 21, 0};
        nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3)); // 1,4,21,0,6,23,66
    }

    // lexicographically greater
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

    }

    private static void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    private static void reverse(int[] nums, int idx1, int idx2) {
        while (idx1 < idx2) {
            swap(nums, idx1, idx2);
            idx1++;
            idx2--;
        }
    }


}