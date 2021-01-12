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

}
