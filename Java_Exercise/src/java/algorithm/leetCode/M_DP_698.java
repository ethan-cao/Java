package algorithm.leetCode;

/*
Given an array of integers nums and a positive integer k,
find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.

### Example
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

*/

public class M_DP_698 {
    public static void main(String... args) {
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4)); // T
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        return true;
    }

}
