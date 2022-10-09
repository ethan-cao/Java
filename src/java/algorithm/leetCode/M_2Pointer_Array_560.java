package algorithm.leetCode;

/*
Given an array of integers and an integer k,
you need to find the total number of continuous subarrays whose sum equals to k.

The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

### Example
Input:nums = [1,1,1], k = 2
Output: 2

*/


import java.util.HashMap;
import java.util.Map;

public class M_2Pointer_Array_560 {

    public static void main(String... args) {
        System.out.println(subarraySum1(new int[]{1, 1, 1, 1}, 2)); // 3
        System.out.println(subarraySum1(new int[]{1, 1, 1, 1}, 3)); // 2
        System.out.println(subarraySum1(new int[]{3, 4, 1, 2}, 5)); // 1
        System.out.println(subarraySum1(new int[]{1, 2, 3}, 3));    // 2
        System.out.println(subarraySum1(new int[]{1}, 0));          // 0
        System.out.println(subarraySum1(new int[]{-1, -1, 1}, 0));  // 1
    }

    // Two Pointer Sliding window
    // it does not work since the array contains negative and 0
    // Sliding window is applicable when there is at most 1 solution per each window range
    // output for case [0,0,0] with k as 0 is 6, which is not the case
    public static int subarraySum0(int[] nums, int k) {
        int subArrayCount = 0;

        if (k == 0) {
            return subArrayCount;
        }

        for (int start = 0; start < nums.length; ) {

            int sum = 0;
            int left = start;
            int right = start;

            while (sum < k && right < nums.length) {
                sum += nums[right];
                right++;
            }

            while (sum > k && left <= right) {
                sum -= nums[left];
                left++;
            }

            if (sum == k) {
                subArrayCount++;
                start = left + 1;
            } else {
                start += 1;
            }
        }

        return subArrayCount;
    }

    // Brute Force
    // Time: O(N^3)
    public static int subarraySum(int[] nums, int k) {
        int subArrayCount = 0;

        for (int left = 0; left < nums.length; ++left) {
            for (int right = left; right < nums.length; ++right) {
                int sum = 0;

                for (int i = left; i <= right; ++i) {
                    sum += nums[i];
                }

                if (sum == k) {
                    subArrayCount++;
                }
            }
        }

        return subArrayCount;
    }

    // Essentially we need sum[i, j], and sum[i, j] = sum[0, j] - sum[0, i-1]
    // So if we know sum[0, i-1] and sum[0, j], then we can easily get sum[i, j]
    public static int subarraySum1(int[] nums, int k) {
        int subArrayCount = 0;

        int sum = 0;
        Map<Integer, Integer> sums = new HashMap<>();  // sum[0, i] -> occurrence

        for (int num : nums) {
            sum += num;

            if (sum == k) {
                subArrayCount++;
            }

            // if key sum-k exists, then there is num before the current num,
            // previousSum + k = sum, it means sum from that num (exclude) to current num is k
            if (sums.containsKey(sum - k)) {
                subArrayCount += sums.get(sum - k);
            }

            sums.put(sum, sums.getOrDefault(sum, 0) + 1);
        }

        return subArrayCount;
    }

}