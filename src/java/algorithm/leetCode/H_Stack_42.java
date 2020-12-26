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
    public static int trap(int[] heights) {
        int water = 0;
        final int L = heights.length;

        // since we need values just besides the local minima, use monotonic decreasing stack
        final Deque<Integer> stack = new ArrayDeque<>();

        for (int rightIdx = 0; rightIdx < L; ++rightIdx) {
            int rightHeight = heights[rightIdx];

            while (!stack.isEmpty() && heights[stack.peek()] < rightHeight) {
                // find the concave 凹 (convex 凸)
                /* the most basic case is
                             _
                         _  | |
                        | |_| |
                        | | | |
                      ------------
                   idx   0 1 2
                  height 2 1 3
                  water = (Math.min(2, 3) - 1) * (2 - 0 - 1)
                 */
                int middleIdx = stack.pop();
                int middleHeight = heights[middleIdx];
                // middleHeight is a local minimum, middleHeight < rightHeight && middleHeight < leftHeight

                if (stack.isEmpty()) {
                    continue;
                }

                int leftIdx = stack.peek();
                int leftHeight = heights[leftIdx];

                int height = Math.min(leftHeight, rightHeight) - middleHeight;
                int width = rightIdx - leftIdx - 1;
                int area = height * width;

                water += area;
            }

            stack.push(rightIdx);
        }

        return water;
    }
}
