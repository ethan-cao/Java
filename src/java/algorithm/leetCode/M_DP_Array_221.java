package algorithm.leetCode;

/*
Given a 2D binary matrix filled with 0's and 1's,
find the largest square containing only 1's and return its area.

### Example
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0  -> 4

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

    // DP, 5ms
    // Time: O(N^2)
    public static int maximalSquare(char[][] matrix) {
        final int M = matrix.length;
        final int N = matrix[0].length;

        // squareLength[i][j] : number of edge of square that ends in matrix[y][x]
        int[][] squareLength = new int[M][N];
        int maxSquareLength = 0;

        // TODO optimization
        // Actually each time when we update squareLength[i][j],
        // we only need squareLength[i-1][j-1], squareLength[i-1][j] and squareLength[i][j-1]
        // So we may just keep two rows, the current and the previous row

        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < N; ++x) {

                if (y == 0 || x == 0) {
                    squareLength[y][x] = matrix[y][x] == '1' ? 1 : 0;
                } else if (matrix[y][x] == '0') {
                    squareLength[y][x] = 0;
                } else {
                    squareLength[y][x] = Math.min(squareLength[y - 1][x - 1], Math.min(squareLength[y - 1][x], squareLength[y][x - 1])) + 1;
                }

                maxSquareLength = Math.max(squareLength[y][x], maxSquareLength);
            }
        }

        return maxSquareLength * maxSquareLength;
    }

}