package algorithm.leetCode;

/*
Given an integer array nums, find the contiguous subarray (containing at least one number)
which has the largest sum and return its sum.
1 <= nums.length <= 3 * 10^4
-10^5 <= nums[i] <= 10^5

Example:
[-2,1,-3,4,-1,2,1,-5,4] -> 6
[4,-1,2,1] has the largest sum = 6.

*/
public class E_DP_Array_53 {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));  //6
    }

    // DP, iterative, condensed space, 0ms
    // Time: O(N)
    public static int maxSubArray1(int[] nums) {
        int maxSum = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            sum = Math.max(0, sum) + nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;

    }

    // DP, iterative, 1ms
    // Time: O(N)
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];

        // maxSums[i]: max sum til nums[i]
        int maxSums[] = new int[nums.length];
        maxSums[0] = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            maxSums[i] = Math.max(maxSums[i - 1], 0) + nums[i];
            maxSum = Math.max(maxSum, maxSums[i]);
        }

        return maxSum;
    }

}
