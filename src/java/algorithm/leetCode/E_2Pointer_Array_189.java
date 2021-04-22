package algorithm.leetCode;

import java.util.Arrays;

/*
Given an array, rotate the array to the right by k steps, where k is non-negative.

### Example
Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]


*/
public class E_2Pointer_Array_189 {

    public static void main(String... args) {
//        int[] input = {1, 2, 3, 4, 5, 6, 7};
        int[] input = {-1,-100,3,99};

        rotate(input, 4);

        System.out.println(Arrays.toString(input));
    }

    public static void rotate(int[] nums, int k) {
        if (k <= 0 ){
            return;
        }

        k = k % nums.length;

        int[] lastKValue = new int[k];
        System.arraycopy(nums, nums.length - k, lastKValue, 0, k);

        for (int i = nums.length - k - 1; i >= 0; --i) {
            nums[i+k] = nums[i];
        }

        System.arraycopy(lastKValue, 0, nums, 0, k);
    }
}
