package algorithm.leetCode;

/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

### Example
[10,9,2,5,3,7,101,18] -> 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

### sub problem

length of longest increasing subsequence for {a1}, {a1, a2}, {a1, a2} ...

*/


import java.util.Arrays;

public class M_DP_Array_300 {
    public static void main(String... args) {
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums1)); // 4

        int[] nums2 = {};
        System.out.println(lengthOfLIS(nums2)); // 0

        int[] nums3 = {1, 1};
        System.out.println(lengthOfLIS(nums3)); // 1

        int[] nums4 = {2, 5, 3, 6, 5, 6, 80};
        System.out.println(lengthOfLIS(nums4)); // 5 = 2,3,5,6,80
    }

    // // https://youtu.be/fV-TF4OvZpk
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxLength = 0;

        // length[i]: length of longest increasing subsequence for num[0...i]
        int[] length = new int[nums.length];
        Arrays.fill(length, 1);

        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (nums[j] < nums[i]) {
                    length[i] = Math.max(length[j] +1, length[i]);
                }
            }

            maxLength = Math.max(maxLength, length[i]);
        }

        return maxLength;
    }

}
