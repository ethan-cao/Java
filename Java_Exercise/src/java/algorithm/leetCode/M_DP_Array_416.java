package algorithm.leetCode;

/*
Given a non-empty array containing only positive integers,
find if the array can be partitioned into 2 subsets such that the sum of elements in both subsets is equal.

Each of the array element will not exceed 100.
The array size will not exceed 200.


### Example
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11]

Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

Related : 518 unbounded knapsack problem
*/


public class M_DP_Array_416 {

    public static void main(String... args) {
        int[] nums1 = {1, 5, 11, 5};
        System.out.println(canPartition1(nums1));  // t

        int[] nums2 = {1, 2, 3, 5};
        System.out.println(canPartition1(nums2));  // f

        int[] nums3 = {1, 3, 6, 1};
        System.out.println(canPartition1(nums3));  // f

        int[] nums4 = {5, 1, 4};
        System.out.println(canPartition1(nums4));  // t

        int[] nums5 = {3, 3, 3, 4, 5};
        System.out.println(canPartition1(nums5));  // t

        int[] nums6 = {1, 5, 11, 5};
        System.out.println(canPartition1(nums6));  // t
    }

    //DP iterative
    public static boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // if sum is odd number, then not possible
        if (sum % 2 != 0) {
            return false;
        }

        // need to find n elements from nums[], which can add up to half
        // 0-1 knapsack problem
        // sums[i][j] : if first i elements can sum up to j
        int half = sum / 2;
        boolean[][] sums = new boolean[nums.length + 1][half + 1];

        for (int i = 0; i <= nums.length; ++i) {
            sums[i][0] = true;
        }

        for (int i = 1; i <= nums.length; ++i) {
            for (int j = 1; j <= half; ++j) {
                int number = nums[i - 1];

                if (number > j) {
                    sums[i][j] = sums[i - 1][j];
                } else {
                    sums[i][j] = sums[i - 1][j] || sums[i - 1][j - number];
                }
            }
        }

        return sums[nums.length - 1][half];
    }

    //DP iterative, optimized
    public static boolean canPartition1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // if sum is odd number, then not possible
        if (sum % 2 != 0) {
            return false;
        }

        int half = sum / 2;

        // By observation, draw and observe from canPartition() and
        // if (number > j) {
        //  sums[i][j] = sums[i - 1][j];
        // } else {
        //  sums[i][j] = sums[i - 1][j] || sums[i - 1][j - number];
        // }
        // sums[i][j] depends on, at most, the previous row
        // it is possible to use 1d array instead
        boolean[] sums = new boolean[half + 1];
        sums[0] = true;

        for (int num : nums) {
            // each outer loop of nums[] refreshes sums[].
            // if iterate from 0 to half, we will get dp[i] from dp[i-num] , while dp[i-num] has been updated in this loop.
            // This dp[i-num] is not the number we got from the previous loop.
            for (int j = half; j >= 0; --j) {
                if (num <= j) {
                    // sums[i][j] = sums[i - 1][j] || sums[i - 1][j - number];
                    sums[j] = sums[j] || sums[j - num];
                    // no need to consider num > j, because  sums[j] = sums[j] when num > j
                }
            }
        }

        return sums[half];
    }

    // Brute force
    public static boolean canPartition2(int[] nums) {
        return false;
    }
}