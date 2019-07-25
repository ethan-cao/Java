package algorithm.leetCode;

/*
Given a 2D binary matrix filled with 0's and 1's,
find the largest square containing only 1's and return its area.

### Example
Input:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4

Related : 85

*/


public class M_DP_Array_221 {

    public static void main(String... args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'}
        };
        System.out.println(maximalSquare(matrix));  // 1

        char[][] matrix1 = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalSquare(matrix1));  // 4
    }

    // DP, https://youtu.be/vkFUB--OYy0?t=1110
    public static int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }

        // localMaxSquareSideLength[i][j] represents edge side of max square that ends in matrix[i][j]
        int[][] localMaxSquareSideLength = new int[matrix.length][matrix[0].length];
        int maxEdge = 0;

        // TODO optimization
        // Actually each time when we update localMaxSquareSideLength[i][j],
        // we only need localMaxSquareSideLength[i-1][j-1], localMaxSquareSideLength[i-1][j] and localMaxSquareSideLength[i][j-1]
        // So we may just keep two rows, the current and the previous row

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {

                if (i == 0 || j == 0) {
                    localMaxSquareSideLength[i][j] = matrix[i][j] == '1' ? 1 : 0;
                } else if (matrix[i][j] == '0') {
                    localMaxSquareSideLength[i][j] = 0;
                } else {
                    localMaxSquareSideLength[i][j] = Math.min(localMaxSquareSideLength[i - 1][j - 1], Math.min(localMaxSquareSideLength[i - 1][j], localMaxSquareSideLength[i][j - 1])) + 1;
                }

                maxEdge = Math.max(localMaxSquareSideLength[i][j], maxEdge);
            }
        }

        return maxEdge * maxEdge;
    }


}