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

public class M_Stack_85 {

    // 7ms
    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        
        if (matrix == null || matrix.length == 0) {
            return maxArea;
        }
        
        final int M = matrix.length;
        final int N = matrix[0].length;
        
        int[] heights = new int[N]; 
        
        for (int row = 0; row < M; ++row) {
            for(int col = 0; col < N; ++col) {
                char element = matrix[row][col];
                heights[col] = element == '1' ? heights[col] + 1 : 0;
            }
      
            maxArea = Math.max(maxArea, M_Stack_84.largestRectangleArea(heights));
        }
        
        return maxArea;
    }

}
