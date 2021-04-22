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
            for (int x = 0; x < N; ++x) {
                char cell = matrix[y][x];

                heights[x] = cell == '1' ? heights[x] + 1 : 0;
            }

            int area = M_Stack_84.largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // Array manipulation
    public int maximalRectangle1(char[][] matrix) {
        int maxArea = 0;

        if (matrix == null || matrix.length == 0) {
            return maxArea;
        }

        final int M = matrix.length;
        final int N = matrix[0].length;
        final char ZERO = '0';
        final char ONE = '1';

        int[] heights = new int[N];// heights[i]: the current number of continuous '1' (from left) in column i
        int[] left = new int[N];  // left[i]: the left most index j, that for any index k from j to i, heights[k] >= heights[i]
        int[] right = new int[N]; // right[i]: the right most index j, that for any index k from i to j, heights[k] >= heights[i]
        // heights[x] is the height for rectangle with right[x] - left[x]

        Arrays.fill(heights, 0);
        Arrays.fill(left, 0);
        Arrays.fill(right, N);  // N-1?

        /*
        matrix = [
            [0 0 0 1 0 0 0]
            [0 0 1 1 1 0 0]
            [0 1 1 1 1 1 0]
        ]

        row 0:  0 0 0 1 0 0 0
        height: 0 0 0 1 0 0 0
        left:   0 0 0 3 0 0 0
        right   7 7 7 4 7 7 7

        row 1:  0 0 1 1 1 0 0
        height: 0 0 1 2 1 0 0
        left:   0 0 2 3 2 0 0
        right:  7 7 5 4 5 7 7

        row 2:  0 1 1 1 1 1 0
        height: 0 1 2 3 2 1 0
        left:   0 1 2 3 2 1 0
        right:  7 6 5 4 5 6 7
        */

        for (int y = 0; y < M; ++y) {

            for (int x = 0; x < N; ++x) {
                if (matrix[y][x] == ONE) {
                    heights[x]++;
                } else {
                    heights[x] = 0;
                }
            }

            int currentLeft = 0;  // left most
            for (int x = 0; x < N; ++x) {
                if (matrix[y][x] == ONE) {
                    left[x] = Math.max(left[x], currentLeft);
                } else {
                    left[x] = 0;
                    currentLeft = x + 1;
                }
            }

            int currentRight = N; // right most  ??? N-1
            for (int x = N - 1; x >= 0; --x) {
                if (matrix[y][x] == ONE) {
                    right[x] = Math.min(right[x], currentRight);
                } else {
                    right[x] = N;
                    currentRight = x;
                }
            }

            for (int x = 0; x < N; ++x) {
                int area = (right[x] - left[x]) * heights[x];
                maxArea = Math.max(maxArea, area);
            }

        }

        return maxArea;
    }

}
