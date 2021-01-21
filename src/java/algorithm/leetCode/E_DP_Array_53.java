package algorithm.leetCode;

/*
Given an integer array nums, find the contiguous subarray (containing at least one number)
which has the largest sum and return its sum.

Example:
[-2,1,-3,4,-1,2,1,-5,4] -> 6
[4,-1,2,1] has the largest sum = 6.

*/
public class E_DP_Array_53 {

    public static void main(String[] args) {
        int[] data1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        System.out.println(maxSubArray(data1));   //6
    }

    // DP, iterative, 1ms
    // Time: O(N)
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        final int L = nums.length;
        int maxSum = nums[0];

        // maxSums[i]: largest sum til nums[i]
        int maxSums[] = new int[L];
        maxSums[0] = nums[0];

        for (int i = 1; i < L; ++i) {
            maxSums[i] = Math.max(maxSums[i-1], 0) + nums[i];
            maxSum = Math.max(maxSum, maxSums[i]);
        }

        return maxSum;
    }


    // DP, iterative, condensed space, 1ms
    public static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        final int L = nums.length;
        int maxSum = nums[0];
        int localMaxSum = nums[0];

        for (int i = 1; i < L; ++i) {
            localMaxSum =  Math.max(localMaxSum, 0) + nums[i];
            maxSum = Math.max(maxSum, localMaxSum);
        }

        return maxSum;
    }

}
