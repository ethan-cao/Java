package algorithm.leetCode;

/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

### Example
[10,9,2,5,3,7,101,18] -> 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

*/


public class M_DP_300 {
    public static void main(String... args) {
        int[] nums1 = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums1)); // 4
    }

    public static int lengthOfLIS(int[] nums) {
        return 1;
    }

}
