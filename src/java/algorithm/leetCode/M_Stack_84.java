package algorithm.leetCode;

/*
Given n non-negative integers representing the histogram's bar height 
where the width of each bar is 1, 
find the area of largest rectangle in the histogram.

### Example
https://leetcode.com/problems/largest-rectangle-in-histogram/
[2,1,5,6,2,3] -> 10

*/

import java.util.*;

public class M_Stack_84 {

    // Stack, 8ms
    // Time: O(N)
    public static int largestRectangleArea0(int[] heights) {
        int maxArea = 0;
        final int L = heights.length;

        Deque<Integer> stack = new ArrayDeque<>();
        int[] leftSmallerIndices = new int[L];

        for (int i = 0; i < L; ++i) {
            int height = heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] >= height) {
                stack.pop();
            }

            leftSmallerIndices[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        int[] rightSmallerIndices = new int[L];

        for (int i = L - 1; i >= 0; --i) {
            int height = heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] >= height) {
                stack.pop();
            }

            rightSmallerIndices[i] = stack.isEmpty() ? L : stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < L; ++i) {
            int area = heights[i] * (rightSmallerIndices[i] - leftSmallerIndices[i] - 1);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // Stack, Extrema, 7ms
    // Time: O(N) Space: O(N)
    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;

        Deque<Integer> stack = new ArrayDeque<>(); // monotonic increasing

        for (int rightIdx = 0; rightIdx < heights.length; ++rightIdx) {
            int right = heights[rightIdx];

            while (!stack.isEmpty() && heights[stack.peek()] >= right) {
                // find the convex 凸 (concave 凹)
                // middle is local maximum, middle >= right && middle > left
                // use the local maximum as elevation to get area
                int middle = heights[stack.pop()];
                int leftIdx = stack.isEmpty() ? -1 : stack.peek();

                int elevation = middle;
                int length = rightIdx - leftIdx - 1;
                int area = elevation * length;

                maxArea = Math.max(maxArea, area);
            }

            stack.push(rightIdx);
        }

        // the rest heights are increasing
        while (!stack.isEmpty()) {
            int elevation = heights[stack.pop()];
            int length = heights.length - (stack.isEmpty() ? 0 : stack.peek() + 1);
            int area = elevation * length;

            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
