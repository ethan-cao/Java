package algorithm.leetCode;

/*
Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] 
such that i < j < k and nums[i] < nums[k] < nums[j].
Return true if there is a 132 pattern in nums, otherwise, return false.
n == nums.length
1 <= n <= 104
-10^9 <= nums[i] <= 10^9

### Example
[1,2,3,4] -> false
[3,1,4,2] -> true
[-1,3,2,0] -> true
[3,5,0,3,4] -> true

*/

import java.util.*;

public class M_Stack_456 {

    // Stack, 3ms
    // Time: O(N), Space: O(N)
    public boolean find132pattern0(int[] nums) {
        // min[i]: the minimum one in subarray nums[0, i)
        int[] mins = Arrays.copyOfRange(nums, 0, nums.length);
        for (int i = 1; i < nums.length; ++i) {
            mins[i] = Math.min(nums[i - 1], mins[i - 1]);
        }

        // stack stores possible values for rightNum
        Deque<Integer> stack = new ArrayDeque<>();  // monotonic decreasing

        // scan from right to left, no need to check middleIdx == 0
        for (int middleIdx = nums.length - 1; middleIdx > 0; --middleIdx) {
            int middleNum = nums[middleIdx];
            int middleMin = mins[middleIdx];

            // if middleNum <= all values on it left, not possible
            if (middleNum <= middleMin) {
                continue;
            }

            // now, there is a i < middleIdx, that nums[i] < nums[middleIdx]

            // find one on the right of middleNum that is > middleMin
            while (!stack.isEmpty() && stack.peek() <= middleMin) {
                stack.pop();
            }

            // if there is one on the right of middleNum that is also < middleNum, call it rightNum
            // since rightNum > middleMin, there is a one on middleNum's left, that < rightNum
            // so we found left < middle < right && leftNum < rightNum < middleNum
            if (!stack.isEmpty() && stack.peek() < middleNum) {
                return true;
            }

            stack.push(middleNum);
        }

        return false;
    }

    // Stack, 6ms
    // Time: O(N), Space: O(N)
    public boolean find132pattern1(int[] nums) {
        // search for a subsequence (leftNum, middleNum, rightNum)
        // that satisfies leftNum < rightNum < middleNum

        int rightNum = Integer.MIN_VALUE;  // set to min, since we need the largest possible one
        Deque<Integer> stack = new ArrayDeque<>(); // monotonic decreasing

        for (int idx = nums.length - 1; idx >= 0; --idx) {
            int num = nums[idx];

            // if there is one on num's right, that is < num,
            // the one could be rightNum, num could be middleNum, so we have rightNum < middleNum
            // all nums in stack is on num's right, to find the largest num in the stack as rightNum
            // to make middleNum as large as possible so we can find possible numLeft,
            // that is why rightNum is set to MIN_VALUE initially
            // since the stack is decreasing, the last pop one is the largest middleNum
            while (!stack.isEmpty() && stack.peek() < num) {
                rightNum = stack.pop();
            }

            if (num < rightNum) {
                // in this case, rightNum was not updated in the above while loop
                // and there is a one on rightNum's left, that is < rightNum, this one can be a leftNum, found leftNum < rightNum
                // since rightNum is not the initial value, there is a valid pair rightNum < Middle,
                // so found 231 pattern
                return true;
            } else {
                // in this case, there is a valid pair rightNum < middleNum
                // rightNum is recorded, middleNum is added to stack
                stack.push(num);
            }
        }

        return false;
    }

}