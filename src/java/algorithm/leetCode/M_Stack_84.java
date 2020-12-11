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

    // Stack, 8ms
    // Time: O(N)
    public int largestRectangleArea0(int[] heights) {
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

    // Simplified with 1 stack and 1 iteration, 7ms
    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i <= heights.length; ++i) {
            int height = i == heights.length ? 0 : heights[i];
            
            while (!stack.isEmpty() && heights[stack.peek()] >= height) {
                int idx = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                
                int area = heights[idx] * (i - left - 1);
                maxArea = Math.max(maxArea, area);
            }
            
            stack.push(i);
        }
        
        return maxArea;
    }
}
