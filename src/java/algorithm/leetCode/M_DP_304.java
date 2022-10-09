package algorithm.leetCode;

/*
Given a 2D matrix matrix, handle multiple queries of the following type:

Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-105 <= matrix[i][j] <= 105
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
At most 104 calls will be made to sumRegion

### Example
Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
Output
[null, 8, 11, 12]

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)

*/

public class M_DP_304 {
    class NumMatrix {
        int[][] sum;

        // Time: O(m*n), Space: O(m*n)
        // 102ms
        public NumMatrix(int[][] matrix) {
            int M = matrix.length;
            int N = matrix[0].length;

            // sum[i][j]: sum of rectangle with bottom right [i, j]
            sum = new int[M][N];

            // BASE
            sum[0][0] = matrix[0][0];

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    int leftRectSum = j - 1 >= 0 ? sum[i][j - 1] : 0;
                    int topRectSum = i - 1 >= 0 ? sum[i - 1][j] : 0;
                    int overlapRectSum = i - 1 >= 0 && j - 1 >= 0 ? sum[i - 1][j - 1] : 0;

                    sum[i][j] = matrix[i][j] + leftRectSum + topRectSum - overlapRectSum;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int leftRectSum = col1 - 1 >= 0 ? sum[row2][col1 - 1] : 0;
            int topRectSum = row1 - 1 >= 0 ? sum[row1 - 1][col2] : 0;
            int overlapRectSum = row1 - 1 >= 0 && col1 - 1 >= 0 ? sum[row1 - 1][col1 - 1] : 0;

            return sum[row2][col2] - leftRectSum - topRectSum + overlapRectSum;
        }
    }
}

