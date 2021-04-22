package algorithm.leetCode;

/*
Given n non-negative integers representing the histogram's bar height 
where the width of each bar is 1, 
find the area of largest rectangle in the histogram.

### Example
[2,1,5,6,2,3] -> 10

*/

import java.util.*;

public class M_Stack_84 {

    // Stack, Extrema, 7ms, similar to 42
    // Time: O(N) Space: O(N)
    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        final int L = heights.length;

        // since we DON NOT need values just besides the local minima, but the local minima to derive the result, use monotonic decreasing stack
        final Deque<Integer> stack = new ArrayDeque<>();

        // calculate area including each bar the bar is used as height
        for (int rightIdx = 0; rightIdx < L; ++rightIdx) {
            int rightHeight = heights[rightIdx];

            while (!stack.isEmpty() && heights[stack.peek()] >= rightHeight) {
                // find the concave 凹 (convex 凸), similar to 907
                /*           _
                           _| |_
                         _| | | |
                        | | | | |
                        | | | | |
                      ------------
                   idx   0 1 2 3
                  height 2 3 4 2

                    rightIdx points to the 1st smaller one on middle's right
                    leftIdx points to the 1st smaller one on middle's left
                    (rightIdx - left - 1) is the count for continuous num that >= middleNum
                    middleIdx points to the minimum value between [leftIdx + 1, rightIdx - 1]
                 */
                int middleIdx = stack.pop();
                int leftIdx = stack.isEmpty() ? -1 : stack.peek();

                int height = heights[middleIdx];
                int width = rightIdx - leftIdx - 1;
                int area = height * width;

                maxArea = Math.max(maxArea, area);
            }

            stack.push(rightIdx);
        }

        // check the rest heights
        // in this case, if stack is not empty, the top one in stack is the last bar in histogram
        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width = (L - 1) - (stack.isEmpty() ? -1 : stack.peek());
            int area = height * width;

            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // Stack, 8ms, similar to algorithm.leetCode.M_Stack_Array_907.sumSubarrayMins1
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
}
