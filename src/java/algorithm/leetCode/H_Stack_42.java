package algorithm.leetCode;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it can trap after raining.

### Example
[4,2,0,3,2,5] -> 9

*/

import java.util.*;

public class H_Stack_42 {

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    // Stack, 1ms
    // Time: O(N)
    public static int trap(int[] height) {
        int water = 0;

        Deque<Integer> stack = new ArrayDeque<>(); // monotonic decreasing

        for (int rightIdx = 0; rightIdx < height.length; ++rightIdx) {
            int rightElevation = height[rightIdx];

            while (!stack.isEmpty() && height[stack.peek()] < rightElevation) {
                // find the concave 凹 (convex 凸)
                // middleElevation is a local minimum, middleElevation < rightElevation && middleElevation < leftElevation
                int middleIdx = stack.pop();
                int middleElevation = height[middleIdx];

                int leftIdx = stack.isEmpty() ? -1 : stack.peek();
                int leftElevation = leftIdx == -1 ? 0 : height[leftIdx];

                int elevation = Math.min(leftElevation, rightElevation) - middleElevation;
                int length = rightIdx - leftIdx - 1;
                water += elevation * length;
            }

            stack.push(rightIdx);
        }

        return water;
    }
}
