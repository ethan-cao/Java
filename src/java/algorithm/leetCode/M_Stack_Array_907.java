package algorithm.leetCode;

/*
Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
Since the answer may be large, return the answer modulo 10^9 + 7.

1 <= A.length <= 30000
1 <= A[i] <= 30000

### Example
[3,1,2,4] -> 17
Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
             Minimums are   3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
             Sum is 17

*/

import java.util.ArrayDeque;
import java.util.Deque;

public class M_Stack_Array_907 {

    public static void main(String... args) {
        System.out.println(sumSubarrayMins1(new int[]{3, 1, 2, 4}));  // 17
//        System.out.println(sumSubarrayMins(new int[]{1, 2, 2, 2}));  // 16
//        System.out.println(sumSubarrayMins(new int[]{3, 9, 1, 2, 4, 8}));  // 49
//        System.out.println(sumSubarrayMins(new int[]{2, 9, 7, 8, 3, 4, 6, 1}));  // 117
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

    // Stack
    // Time: O(N), Space: O(N)
    // sumOfMinimums = ∑ ( A[i] * subarrayCount[i] )
    // where subarrayCount[i] is count of subarray that contains A[i] as the minimum
    //
    // given subarrayCount[i] = continuousLeftLargerItemCount[i] * continuousRightLargerItemCount[i]
    // thus sumOfMinimums = ∑ ( A[i] * continuousLeftLargerItemCount[i] * continuousRightLargerItemCount[i] )
    //
    // why subarrayCount[i] = continuousLeftLargerItemCount[i] * continuousRightLargerItemCount[i] ?
    //
    // let's say A = [2, 9, 7, 8, 3, 4, 6, 1], and we are inspecting A[4], which is 3.
    // continuousLeftLargerItemCount.length = [9, 7, 8, 3].length, which is 4
    // continuousRightLargerItemCount.length = [3, 4, 6].length, which is 3
    //
    // As long as a subarray picks its start item from [9, 7, 8, 3] and end item from [3, 4, 6], it is guaranteed that 3 is the minimum
    // count for subarray that picks its start item from [9, 7, 8, 3] and end item from [3, 4, 6] is [9, 7, 8, 3].length * [3, 4, 6].length
    // For these 12 subarrays, it is guaranteed that 3 is the minimum.
    //
    // Then use monotonically increasing stack to get continuousLeftLargerItemCount.length and continuousRightLargerItemCount.length.
    public static int sumSubarrayMins(int[] A) {
        int sumOfMinimums = 0;
        int mod = (int) (1e9 + 7);

        // they both include the item in A
        int[] continuousLeftLargerItemCount = new int[A.length];
        int[] continuousRightLargerItemCount = new int[A.length];

        // stack element: [item's idx in A, item in A]
        // this is a monotonically increasing stack: the upper one [1] > the lower one [1]
        // use a monotonically increasing stack to get continuousLeftLargerItemCount, continuousRightLargerItemCount
        // each elements is pushed and popped at most once: O(N)
        Deque<int[]> stack = new ArrayDeque<>();

        for (int i = 0; i < A.length; ++i) {
            // use >= for strictly increasing
            // since there could be duplicates, use strictly increasing for continuousLeftLargerItemCount,
            // so we can count adjacent duplicates in subarray, e.g. [1,2,2,2]
            // just need to count from 1 side, either continuousLeftLargerItemCount or continuousRightLargerItemCount
            while (!stack.isEmpty() && stack.peekFirst()[1] >= A[i]) {
                stack.pop();
            }

            // if stack is empty, A[0] til A[i] is >= A[i]
            // if stack is not empty, stack.peekFirst()[0] is the left most item that is < A[i]
            continuousLeftLargerItemCount[i] = stack.isEmpty() ? i + 1 : i - stack.peekFirst()[0];

            stack.push(new int[]{i, A[i]});
        }

        stack.clear();

        for (int i = A.length - 1; i >= 0; --i) {
            // use > for non-strictly increasing
            while (!stack.isEmpty() && stack.peek()[1] > A[i]) {
                stack.pop();
            }

            // if stack is empty, A[i] til A[A.length.-1] is > A[i]
            // if stack is not empty, stack.peekFirst()[0] is the right most item that is <= A[i]
            continuousRightLargerItemCount[i] = stack.isEmpty() ? A.length - i : stack.peekFirst()[0] - i;

            stack.push(new int[]{i, A[i]});
        }

        // sumOfMinimums = ∑ (A[i] * continuousLeftLargerItemCount[i] * continuousRightLargerItemCount[i] )
        for (int i = 0; i < A.length; ++i) {
            // modulo here to prevent overflow
            sumOfMinimums = (sumOfMinimums + A[i] * continuousLeftLargerItemCount[i] * continuousRightLargerItemCount[i]) % mod;
        }

        return sumOfMinimums;
    }

    // 1 stack and 1 iteration
    // Time: O(N), Space: O(N)
    public static int sumSubarrayMins1(int[] A) {
        int sumOfMinimums = 0;
        int mod = (int) (1e9 + 7);

        Deque<Integer> stack = new ArrayDeque<>();

        // sumOfMinimums = ∑ ( n * (rightIdx - currentIdx) * (leftIdx - currentIdx) )

        for (int rightIdx = 0; rightIdx <= A.length; ++rightIdx) {
            int item = rightIdx == A.length ? Integer.MIN_VALUE : A[rightIdx];

            while (!stack.isEmpty() && A[stack.peekFirst()] > item) {
                int currentIdx = stack.pop();
                int leftIdx = stack.isEmpty() ? -1 : stack.peekFirst();

                // currentIdx points to the item we are inspecting,
                // rightIdx points to the the 1st smaller item on currentIdx's right
                // leftIdx points to the the 1st smaller item on currentIdx's left
                // when A[j] is removed from the monotonically increasing stack,
                // ( i - j ) is the count for continuous left larger item
                // ( j - k ) is the count for continuous right larger item
                sumOfMinimums = (sumOfMinimums + A[currentIdx] * (rightIdx - currentIdx) * (currentIdx - leftIdx)) % mod;
            }

            stack.push(rightIdx);
        }

        return sumOfMinimums;
    }

}