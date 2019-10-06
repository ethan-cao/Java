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

public class M_Array_209 {

    public static void main(String... args) {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));  // 2
    }

    public static int minSubArrayLen(int s, int[] nums) {
        return 1;
    }

}