package algorithm.leetCode;

/*
Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
Since the answer may be large, return the answer modulo 10^9 + 7.
1 <= A.length <= 30000
1 <= A[i] <= 30000

### Example
[3,1,2,4] -> 17
Sub-arrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are   3,    1,  2,   4,   1,     1,     2,     1,       1,       1.
Sum is 17

*/

import java.util.*;

public class M_Stack_Array_907 {

    public static void main(String... args) {
        System.out.println(sumSubarrayMins2(new int[]{3, 1, 2, 4})); // 17
        // System.out.println(sumSubarrayMins(new int[]{1, 2, 2, 2})); // 16
        // System.out.println(sumSubarrayMins(new int[]{3, 9, 1, 2, 4, 8})); // 49
        // System.out.println(sumSubarrayMins(new int[]{2, 9, 7, 8, 3, 4, 6, 1})); // 117
    }

    // Stack, Extreme, 23ms
    // Time: O(N), Space: O(N)
    public static int sumSubarrayMins2(int[] nums) {
        long sumOfMinimums = 0;
        final long MOD = (long) (1e9 + 7); // or just 1000000007
        final int L = nums.length;

        // since we DON NOT need values just besides the local minima, but the local minima to derive the result, use monotonic decreasing stack
        Deque<Integer> stack = new ArrayDeque<>();

        for (int rightIdx = 0; rightIdx <= L; ++rightIdx) {
            int rightNum = rightIdx == L ? 0 : nums[rightIdx];

            while (!stack.isEmpty() && nums[stack.peek()] > rightNum) {
                // find the concave 凹 (convex 凸)
                int middleIdx = stack.pop();
                int leftIdx = stack.isEmpty() ? -1 : stack.peek();

                // rightIdx points to the 1st smaller one on middle's right
                // leftIdx points to the 1st smaller one on middle's left
                // (rightIdx - middleIdx) is the count for continuous num that >= middleNum
                // (middleIdx - leftIdx) is the count for continuous num that >= middleNum
                // middleIdx points to the minimum value between [leftIdx + 1, rightIdx - 1]
                int middleNum = nums[middleIdx];
                int possibleSubArrayFirstItemCount = middleIdx - leftIdx;
                int possibleSubArrayLastItemCount = rightIdx - middleIdx;

                long minSum = (middleNum % MOD) * possibleSubArrayFirstItemCount * possibleSubArrayLastItemCount % MOD;
                sumOfMinimums = (sumOfMinimums + minSum) % MOD;
            }

            stack.push(rightIdx);
        }

        return (int) sumOfMinimums;
    }

    // Stack, 28ms
    // Time: O(N), Space: O(N)
    /*
     * sumOfMinimums = ∑ ( arr[i] * subarrayCount[i] )
     * where subarrayCount[i] is count of subarray that contains arr[i] as the minima
     *
     * given subarrayCount[i] = continuousLeftLargerItemCount[i] *
     * continuousRightLargerItemCount[i] thus sumOfMinimums = ∑ ( arr[i] *
     * continuousLeftLargerItemCount[i] * continuousRightLargerItemCount[i] )
     *
     * why subarrayCount[i] = continuousLeftLargerItemCount[i] *
     * continuousRightLargerItemCount[i] ?
     *
     * let's say arr = [2, 9, 7, 8, 3, 4, 6, 1], and we are inspecting arr[4], which is
     * 3. continuousLeftLargerItemCount.length = [9, 7, 8, 3].length, which is 4
     * continuousRightLargerItemCount.length = [3, 4, 6].length, which is 3
     *
     * As long as a subarray picks its start item from [9, 7, 8, 3] and end item
     * from [3, 4, 6], it is guaranteed that 3 is the minimum count for subarray
     * that picks its start item from [9, 7, 8, 3] and end item from [3, 4, 6] is
     * [9, 7, 8, 3].length * [3, 4, 6].length For these 12 subarrays, it is
     * guaranteed that 3 is the minimum.
     */
    public static int sumSubarrayMins1(int[] arr) {
        long sumOfMinimums = 0; // use long to hold int
        final long MOD = (long) (1e9 + 7); // or just 1000000007
        final int L = arr.length;

        int[] leftMostSmallerIndices = new int[L];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < L; i++) {
            // find the concave 凹 (convex 凸)
            // find the left most smaller one
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                // !!! use >= in left, so use < in right
                // e.g. [71,55,82,55], for 55 at index 1, if we count[55,82,55],
                // then for 55 at index 3, no need to count [55,82,55]
                stack.pop();
            }

            leftMostSmallerIndices[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        int[] rightMostSmallerIndices = new int[L];

        for (int i = L - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }

            rightMostSmallerIndices[i] = stack.isEmpty() ? L : stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < L; i++) {
            // modulo early to prevent overflow, need 3 times to prevent overflow
            sumOfMinimums += arr[i] % MOD * (i - leftMostSmallerIndices[i]) * (rightMostSmallerIndices[i] - i) % MOD;
            sumOfMinimums %= MOD;
        }

        return (int) sumOfMinimums;
    }

    // Brute force, to slow to be accepted
    // Time: O(N^3), Space: O(1)
    public static int sumSubarrayMins3(int[] A) {
        long sumOfMinimums = 0;

        for (int startIdx = 0; startIdx < A.length; ++startIdx) {
            for (int endIdx = startIdx; endIdx < A.length; ++endIdx) {

                int min = Integer.MAX_VALUE;

                for (int i = startIdx; i <= endIdx; ++i) {
                    min = Math.min(min, A[i]);
                }

                sumOfMinimums += min;
            }
        }

        return (int) sumOfMinimums % 1000000007;
    }
}