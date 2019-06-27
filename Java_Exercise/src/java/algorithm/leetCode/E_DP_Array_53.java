package algorithm.leetCode;

/*
Given an integer array nums, find the contiguous subarray (containing at least one number)
which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
*/
public class E_DP_Array_53 {

    public static void main(String[] args) {
        int[] data1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray1(data1));   //6
    }

    // DP, Kadane's Algorithm
    // https://en.wikipedia.org/wiki/Maximum_subarray_problem
    public static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // maxSubArray(A, i) = Math.max(maxSubArray(A, i-1), 0)  +  A[i]
        // i could be 1, 2, .. nums.length -1

        int maxSum = nums[0];
        int localMaxSum = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            localMaxSum = nums[i] + Math.max(localMaxSum, 0);
            maxSum = Math.max(maxSum, localMaxSum);
        }

        return maxSum;
    }

}
