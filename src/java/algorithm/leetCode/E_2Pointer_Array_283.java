package algorithm.leetCode;

/*
Given an array nums, write a function to move all 0's to
the end of it while maintaining the relative order of the non-zero elements.

### Example
[0,1,0,3,12] -> [1,3,12,0,0]

*/

import java.util.Arrays;

class E_2Pointer_Array_283 {
    public static void main(String[] args) {
        int[] input = {0, 1, 0, 3, 12, 0, 0};
//        int[] input = {0};
//        int[] input = null;
//        int[] input = {0, 0, 1};

        moveZeroes1(input);

        System.out.println(Arrays.toString(input));
    }

    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }

        // start from the last non 0 position
        int idx = nums.length - 1;
        while (idx >= 0 && nums[idx] == 0) {
            idx--;
        }

        for (int i = 0; i <= idx; ) {
            if (nums[i] == 0) {
                moveToN(nums, i, idx);
                idx--;
            } else {
                i++;
            }
        }

    }

    private static void moveToN(int[] array, int n, int m) {
        for (int i = n; i < m; ++i) {
            swap(array, i, i + 1);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }

        int leftMostZeroIndex = 0; // assuming the 1st value is 0

        // find the 1st non-zero value and swap with the 1st zero value
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                if (i > leftMostZeroIndex) {
                   nums[leftMostZeroIndex] = nums[i];
                   nums[i] = 0;
                }

                leftMostZeroIndex++;
            }
        }
    }
}