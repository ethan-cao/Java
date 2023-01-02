package algorithm.leetCode;

/*
An integer array is called arithmetic if it consists of at least three elements
and if the difference between any two consecutive elements is the same.
For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.

Given an integer array nums, return the number of arithmetic subarrays of nums.
A subarray is a contiguous subsequence of the array.

1 <= nums.length <= 5000
-1000 <= nums[i] <= 1000

### Example
[1] -> 0
[1,2,3,4] -> 3
Explanation: 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4], [1,2,3,4]

*/

public class M_DP_413 {

    // DP 0ms
    // Time: o(N), Space: O(n)
    public int numberOfArithmeticSlices1(int[] nums) {
        int L = nums.length;

        if (L < 3) {
            return 0;
        }

        int count = 0;

        //  counts[i]: number of arithmetic subarray ending at i
        int[] counts = new int[L];
        counts[0] = 0;
        counts[1] = 0;

        // TRANSFORM
        // counts[i] = ( nums[i]-nums[i-1] == nums[i-1] - nums[i-2] )? 1 + counts[i-1] : 0
        for (int idx = 2; idx < L; ++idx) {

            if (nums[idx] - nums[idx - 1] == nums[idx - 1] - nums[idx - 2]) {
                counts[idx] = counts[idx - 1] + 1;
            }

            count += counts[idx];
        }

        return count;
    }

    // DP, 0ms
    // Time: O(N), Space: O(1)
    public int numberOfArithmeticSlices2(int[] nums) {
        int L = nums.length;

        if (L < 3) {
            return 0;
        }

        int count = 0;
        int currentCount = 0;

        for (int idx = 2; idx < L; ++idx)

            if (nums[idx] - nums[idx - 1] == nums[idx - 1] - nums[idx - 2]) {
                currentCount += 1;
                count += currentCount;
            } else {
                currentCount = 0;
            }

        return count;
    }

    // DP, 240ms
    // Time: O(N^2), Space: O(n^2)
    public static int numberOfArithmeticSlices3(int[] nums) {
        int L = nums.length;

        if (L < 3) {
            return 0;
        }

        boolean[][] isArithmetic = new boolean[L][L];
        int count = 0;

        // BASE
        // if any size 3 subarray is arithmetic
        for (int start = 0; start < L - 3 + 1; ++start) {
            if (nums[start + 1] - nums[start] == nums[start + 2] - nums[start + 1]) {
                int end = getEndIdx(start, 3);
                isArithmetic[start][end] = true;
                count++;
            }
        }

        // TRANSFORM
        // isArithmetic[i][j] = isArithmetic[i+1][j] && (nums[i+1] - nums[i] == nums[i+2] - nums[i+1])
        // isArithmetic[i][j] = isArithmetic[i][j-1] && (nums[j] - nums[j-1] == nums[j-1] - nums[j-2])

        // check subarray with length >= 4
        for (int length = 4; length <= L; ++length) {
            for (int start = 0; start < L - length + 1; ++start) {

                int end = getEndIdx(start, length);

                if (isArithmetic[start + 1][end]) {
                    if (nums[start + 1] - nums[start] == nums[start + 2] - nums[start + 1]) {
                        isArithmetic[start][end] = true;
                        count++;
                    }
                } else if (isArithmetic[start][end - 1]) {
                    if (nums[end] - nums[end - 1] == nums[end - 1] - nums[end - 2]) {
                        isArithmetic[start][end] = true;
                        count++;
                    }
                }

            }
        }

        return count;
    }

    private static int getEndIdx(int start, int length) {
        return start + length - 1;
    }
}

