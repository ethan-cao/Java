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
        int[] mins = Arrays.copyOf(nums, nums.length);
        for (int i = 1; i < nums.length; ++i) {
            mins[i] = Math.min(nums[i - 1], mins[i - 1]);
        }

        // stack stores possible values for nums[k]
        Deque<Integer> stack = new ArrayDeque<>();  // monotonic decreasing

        for (int j = nums.length - 1; j >= 0; --j) {
            int num = nums[j];  // nums[j]
            int min = mins[j];

            // if num <= all values on it left, not possible
            if (num <= min) {
                continue;
            }

            // now, there is a i < j, that nums[i] < nums[j]

            while (!stack.isEmpty() && stack.peek() <= min) {
                stack.pop();
            }

            // now, all value in stack > min, namely all possible nums[k] > nums[i]
            // if there is k > j, that nums[k] < nums[j], found
            if (!stack.isEmpty() && stack.peek() < num) {
                return true;
            }

            stack.push(num);
        }

        return false;
    }

    // Stack, 6ms
    // Time: O(N), Space: O(N)
    public boolean find132pattern1(int[] nums) {
        // search for a subsequence (nums[left], nums[middle], nums[right])
        // such that left < middle < right and nums[i] < nums[right] < nums[middle]

        // stack stores possible values for nums[right]
        Deque<Integer> stack = new ArrayDeque<>(); // monotonic decreasing

        // nums[middle]
        int middle = Integer.MIN_VALUE;

        for (int leftIdx = nums.length - 1; leftIdx >= 0; --leftIdx) {
            int left = nums[leftIdx];

            // if there is a value < middle, there is 231 pattern
            // since
            if (left < middle) {
                return true;
            }

            // if there is a value > possible nums[right], pop() it to middle
            while (!stack.isEmpty() && stack.peek() < left) {
                // since the stack is decreasing, the last pop one is the largest middle
                // make middle as large as possbile so we can find possible left
                middle = stack.pop();
            }

            // add the new possbible value for nums[right]
            // all items in stack > middle
            stack.push(left);
        }

        return false;
    }

}