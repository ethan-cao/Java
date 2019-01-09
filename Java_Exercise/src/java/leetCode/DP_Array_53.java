package leetCode;

/*
Given an integer array nums, find the contiguous subarray (containing at least one number)
which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
*/
public class DP_Array_53 {
    public static void main(String[] args){

    }

    /**
     *  Dynamic programming, Kadane's Algorithm
     */
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        // maxSubArray(A, i) = Math.max(maxSubArray(A, i-1), 0) + A[i];
        // i could be 1, 2, .. nums.length -1

        int maxSum = nums[0];
        int localSum = nums[0];

        for (int i = 1; i < nums.length; ++i){
            localSum = Math.max(localSum, 0) + nums[i];
            maxSum = Math.max(localSum, maxSum) ;
        }

        return maxSum;
    }

    /**
     *  Divide and conquer
     */
    public int maxSubArray2(int[] nums) {
        int maxSum = 0;

        return maxSum;
    }
}
