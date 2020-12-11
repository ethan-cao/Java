package algorithm.leetCode;

/*
Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
Since the answer may be large, return the answer modulo 10^9 + 7.

1 <= A.length <= 30000
1 <= A[i] <= 30000

### Example
[3,1,2,4] -> 17
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are   3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17

*/

import java.util.*;

public class M_Stack_Array_907 {

    public static void main(String... args) {
        System.out.println(sumSubarrayMins1(new int[] { 3, 1, 2, 4 })); // 17
        // System.out.println(sumSubarrayMins(new int[]{1, 2, 2, 2})); // 16
        // System.out.println(sumSubarrayMins(new int[]{3, 9, 1, 2, 4, 8})); // 49
        // System.out.println(sumSubarrayMins(new int[]{2, 9, 7, 8, 3, 4, 6, 1})); // 117
    }

    // Stack, 28ms
    /*
    Time: O(N), Space: O(N)
    sumOfMinimums = ∑ ( A[i] * subarrayCount[i] )
    where subarrayCount[i] is count of subarray that contains A[i] as the minimum
    
    given subarrayCount[i] = continuousLeftLargerItemCount[i] *
    continuousRightLargerItemCount[i]
    thus sumOfMinimums = ∑ ( A[i] * continuousLeftLargerItemCount[i] *
    continuousRightLargerItemCount[i] )
    
    why subarrayCount[i] = continuousLeftLargerItemCount[i] *
    continuousRightLargerItemCount[i] ?
    
    let's say A = [2, 9, 7, 8, 3, 4, 6, 1], and we are inspecting A[4], which is 3.
    continuousLeftLargerItemCount.length = [9, 7, 8, 3].length, which is 4
    continuousRightLargerItemCount.length = [3, 4, 6].length, which is 3
    
    As long as a subarray picks its start item from [9, 7, 8, 3] and end item
    from [3, 4, 6], it is guaranteed that 3 is the minimum
    count for subarray that picks its start item from [9, 7, 8, 3] and end item
    from [3, 4, 6] is [9, 7, 8, 3].length * [3, 4, 6].length
    For these 12 subarrays, it is guaranteed that 3 is the minimum.
    */
    public int sumSubarrayMins00(int[] A) {
        long sumOfMinimums = 0; // use long to hold int
        final long MOD = (long) (1e9 + 7); // or just 1000000007
        final int L = A.length;

        int[] leftSmallerIndices = new int[L];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < L; i++) {
            // !!! use >= in left, so use < in right
            while (!stack.isEmpty() && A[stack.peek()] >= A[i]) {
                stack.pop();
            }

            leftSmallerIndices[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        int[] rightSmallerIndices = new int[L];

        for (int i = L - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
                stack.pop();
            }

            rightSmallerIndices[i] = stack.isEmpty() ? L : stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < L; i++) {
            // modulo early to prevent overflow, need 3 times to prevent overflow
            sumOfMinimums += (i - leftSmallerIndices[i]) * (rightSmallerIndices[i] - i) % MOD * A[i] % MOD;
            sumOfMinimums %= MOD;
        }

        return (int) sumOfMinimums;
    }

    // Simplified with 1 stack and 1 iteration, 23ms
    // Time: O(N), Space: O(N)
    public static int sumSubarrayMins1(int[] A) {
        int sumOfMinimums = 0;
        final long MOD = (long) (1e9 + 7); // or just 1000000007

        Deque<Integer> stack = new ArrayDeque<>();  // monotonic increasing

        for (int rightIdx = 0; rightIdx <= A.length; ++rightIdx) {
            int item = rightIdx == A.length ? 0 : A[rightIdx];

            while (!stack.isEmpty() && A[stack.peek()] > item) {
                int currentIdx = stack.pop();
                int leftIdx = stack.isEmpty() ? -1 : stack.peek();

                // since stack is monotonic increasing
                // leftIdx points to the 1st smaller item on currentIdx's left
                // rightIdx points to the 1st smaller item on currentIdx's right

                sumOfMinimums += (rightIdx - currentIdx) * (currentIdx - leftIdx) % MOD * A[currentIdx] % MOD;
                sumOfMinimums %= MOD;
            }

            stack.push(rightIdx);
        }

        return sumOfMinimums;
    }

    // Brute force, to slow to be accepted
    // Time: O(N^3), Space: O(1)
    public static int sumSubarrayMins0(int[] A) {
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