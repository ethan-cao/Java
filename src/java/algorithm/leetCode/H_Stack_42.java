package algorithm.leetCode;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it can trap after raining.

### Example
https://leetcode.com/problems/trapping-rain-water/

*/

import java.util.*;

public class H_Stack_42 {

    // Stack, 1ms
    // Time: O(N)
    public int trap(int[] height) {
        int water = 0;

        Deque<Integer> stack = new ArrayDeque<>(); // monotonic descreasing

        for (int i = 0; i < height.length; i++) {
            int right = height[i];

            while (!stack.isEmpty() && height[stack.peek()] < right) {
                // find the concave 凹, (convex 凸)
                // middle is local minimum, middle < right && middle < left
                int middle = height[stack.pop()];

                if (stack.isEmpty()) {
                    break;
                }

                int left = height[stack.peek()];

                int elevation = Math.min(left, right) - middle;
                int length = i - stack.peek() - 1;

                water += elevation * length;
            }

            stack.push(i);
        }

        return water;
    }
}
