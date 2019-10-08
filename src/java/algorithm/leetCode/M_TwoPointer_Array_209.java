package algorithm.leetCode;

/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.

### Example
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

*/

public class M_TwoPointer_Array_209 {

    public static void main(String... args) {
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));  // 2
        System.out.println(minSubArrayLen(3, new int[]{1, 1}));  // 0
    }

    // Time: O(n)
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int minLength = Integer.MAX_VALUE;
        int sum = 0;

        // since we are looking for subarray length, naturally think about using 2 pointer
        for (int left = 0, right = 0; right < nums.length; ++right) {
            sum += nums[right];

            while (sum >= s) {
                // length of current subarray is (rigth - left + 1)
                minLength = Math.min(minLength, right - left + 1);

                sum -= nums[left];
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

}