package algorithm.leetCode;

/*
Given a circular array (the next element of the last element is the first element of the array),
print the Next Greater Number for every element.
The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
which means you could search circularly to find its next greater number.
If it doesn't exist, output -1 for this number.

The length of given array won't exceed 10000.

### Example
[1,2,1] -> [2,-1,2]

*/

import java.util.*;

public class M_Stack_Array_503 {

    public static void main(String... args) {
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1, 2, 1}))); // 2, -1, 2
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1, 2, 14, 2, 1, 0}))); // 2, 14, -1, 14, 2, 1
    }

    // Stack, 4ms
    // Time: O(N), Space: O(N)
    public static int[] nextGreaterElements(int[] nums) {
        int[] nextGreater = new int[nums.length];
        Arrays.fill(nextGreater, -1);

        Deque<Integer> stack = new ArrayDeque<>(); // monotonic decreasing

        // !!! extend the array to handle circular array
        for (int i = 0; i < nums.length * 2; ++i) {
            int num = nums[i % nums.length];

            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                int smallerIdx = stack.pop();
                nextGreater[smallerIdx] = num;
            }

            // store index only once
            if (i < nums.length) {
                stack.push(i);
            }
        }

        return nextGreater;
    }

    // Stack
    // Time: O(N), Space: O(N)
    public static int[] nextGreaterElements1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        // default value is 0, not good. use -1, assume no larger element by default
        int[] nextGreaterElements = new int[nums.length];
        Arrays.fill(nextGreaterElements, -1);

        // store element from right to left
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            stack.push(nums[i]);
        }

        for (int i = nums.length - 1; i >= 0; --i) {

            // look from left to right, find the next greater element for nums[i]
            while (!stack.isEmpty() && stack.peekFirst() <= nums[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                nextGreaterElements[i] = stack.peekFirst();
            }

            // put the processed element to stack top, since it is circular array
            stack.push(nums[i]);
        }

        return nextGreaterElements;
    }

    // Time: O(N^2), Space: O(N)
    public static int[] nextGreaterElements0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        int[] nextGreaterElements = new int[nums.length];
        Arrays.fill(nextGreaterElements, -1);

        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < i + nums.length; ++j) {
                int currentElement = nums[i];
                int nextElement = nums[j % nums.length];

                if (nextElement > currentElement) {
                    nextGreaterElements[i] = nextElement;
                    break;
                }
            }
        }

        return nextGreaterElements;
    }
}