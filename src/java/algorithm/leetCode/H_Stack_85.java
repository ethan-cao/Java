package algorithm.leetCode;

/*
Given a rows x cols binary matrix filled with 0's and 1's, 
find the largest rectangle containing only 1's and return its area.

rows == matrix.length
cols == matrix.length
0 <= row, cols <= 200
matrix[i][j] is '0' or '1'.

### Example
[ ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"] ]
Output: 6

[] -> 0
[["0"]] -> 0
*/

import java.util.Arrays;

public class H_Stack_85 {

    // Stack, 4ms
    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        
        if (matrix == null || matrix.length == 0) {
            return maxArea;
        }
        
        final int M = matrix.length;
        final int N = matrix[0].length;
        
        int[] heights = new int[N]; 
        
        for (int y = 0; y < M; ++y) {
            for(int x = 0; x < N; ++x) {
                char cell = matrix[y][x];

                heights[x] = cell == '1' ? heights[x] + 1 : 0;
            }

            int area = M_Stack_84.largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }

    // DP
    // https://leetcode.com/problems/maximal-rectangle/discuss/29054/share-my-dp-solution
    // height[i] record the current number of continuous '1' in column i;
    // left[i] record the left most index j which satisfies that for any index k from j to  i, height[k] >= height[i];
    // right[i] record the right most index j which satisfies that for any index k from i to  j, height[k] >= height[i];
    // then we need to update maxArea with value (height[i] * (right[i] - left[i] + 1));
    public int maximalRectangle1(char[][] matrix) {
        int maxArea = 0;

        if (matrix == null || matrix.length == 0) {
            return maxArea;
        }

        final int M = matrix.length;
        final int N = matrix[0].length;

        int[] height = new int[N];
        int[] left = new int[N];
        int[] right = new int[N];

        Arrays.fill(height, 0);
        Arrays.fill(left, 0);
        Arrays.fill(right, N);

        for (int y = 0; y < M; y++) {
            int cur_left = 0;
            int cur_right = N;

            for (int x = 0; x < N; x++) {
                if (matrix[y][x] == '1') {
                    height[x]++;
                } else {
                    height[x] = 0;
                }
            }

            // compute left (from left to right)
            for (int x = 0; x < N; x++) {
                if (matrix[y][x] == '1') {
                    left[x] = Math.max(left[x], cur_left);
                } else {
                    left[x] = 0;
                    cur_left = x + 1;
                }
            }

            // compute right (from right to left)
            for (int x = N - 1; x >= 0; x--) {
                if (matrix[y][x] == '1') {
                    right[x] = Math.min(right[x], cur_right);
                } else {
                    right[x] = N;
                    cur_right = x;
                }
            }

            // compute the area of rectangle (can do this from either side)
            for (int x = 0; x < N; x++) {
                maxArea = Math.max(maxArea, (right[x] - left[x]) * height[x]);
            }

        }

        return maxArea;
    }

}
