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
        System.out.println(maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 })); // 6
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Kadane's algorithm
    // DP, iterative, condensed space, 0ms
    // Time: O(N)
    public static int maxSubArray2(int[] nums) {
        final int L = nums.length;

        int maxSum = nums[0];
        int localMaxSum = nums[0];

        for (int i = 1; i < L; ++i) {
            int num = nums[i];

            // must always includes num, this case is subarray including num
            int newArraySum = num;
            int appendingArraySum = localMaxSum + num;
            localMaxSum = Math.max(newArraySum, appendingArraySum);

            maxSum = Math.max(maxSum, localMaxSum);
        }

        return maxSum;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Kadane's algorithm
    // DP, iterative, 1ms
    // Time: O(N)
    public static int maxSubArray3(int[] nums) {
        final int L = nums.length;

        int maxSum = nums[0];

        // maxSums[i]: max subarray sum including nums[i]
        int maxSums[] = new int[L];

        // BASE
        maxSums[0] = nums[0];

        for (int i = 1; i < L; ++i) {
            // TRANSFORM
            // must always includes num, either a new one or with maxSums[i - 1]
            // because maxSums[i-1] always includes the previous num
            int appendingArray = maxSums[i - 1] + nums[i];
            int newArray = nums[i];
            maxSums[i] = Math.max(appendingArray, newArray);

            maxSum = Math.max(maxSum, maxSums[i]);
        }

        return maxSum;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, recursive
    // 35ms
    public int maxSubArray1(int[] nums) {
        final int L = nums.length;
        int max = (int) -1e4;

        Integer[] memo = new Integer[L];

        for (int end = L - 1; end >= 0; --end) {
            int localMax = find(nums, end, memo);
            max = Math.max(max, localMax);
        }

        return max;
    }

    // find maxSubArray ending at end
    private int find(int[] nums, int end, Integer[] memo) {
        if (memo[end] != null) {
            return memo[end];
        }

        if (end == 0) {
            memo[end] = nums[end];
            return memo[end];
        }

        int appendingArray = find(nums, end - 1, memo) + nums[end];
        int newArray = nums[end];

        memo[end] = Math.max(appendingArray, newArray);

        return memo[end];
    }

}
