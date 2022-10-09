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

    // DP, iterative, 4ms
    // Time: O(N^2), Space: O(M*N)
    public static int maximalSquare(char[][] matrix) {
        final int M = matrix.length;
        final int N = matrix[0].length;
        final char ZERO = '0';
        final char ONE = '1';

        // squareLength[i][j] : number of edge of square that ends in matrix[y][x]
        int[][] squareLength = new int[M][N];
        int maxSquareLength = Integer.MIN_VALUE;

        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < N; ++x) {
                char cell = matrix[y][x];

                // Actually each time when we update squareLength[i][j],
                // we only need squareLength[i-1][j-1], squareLength[i-1][j] and squareLength[i][j-1]
                // So we may just keep two rows, the current and the previous row
                if (cell == ONE) {
                    if (y == 0 || x == 0) {
                        squareLength[y][x] = 1;
                    } else {
                        squareLength[y][x] = Math.min(squareLength[y - 1][x - 1], Math.min(squareLength[y - 1][x], squareLength[y][x - 1])) + 1;
                    }
                }

                maxSquareLength = Math.max(maxSquareLength, squareLength[y][x]);
            }
        }

        return maxSquareLength * maxSquareLength;
    }

    // DP, condensed space, ms
    // Time: O(N^2), Space: O(N)
    public static int maximalSquare1(char[][] matrix) {
        final int M = matrix.length;
        final int N = matrix[0].length;
        final char ZERO = '0';
        final char ONE = '1';

        int topLeftLength = Integer.MAX_VALUE;  // squareLength[y-1][x-1]
        int[] squareLength = new int[N];
        int maxSquareLength = Integer.MIN_VALUE;

        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < N; ++x) {
                char cell = matrix[y][x];

                int temp = squareLength[x];

                if (cell == ONE) {
                    if (y == 0 || x == 0) {
                        squareLength[x] = 1;
                    } else {
                        squareLength[x] = Math.min(topLeftLength, Math.min(squareLength[x], squareLength[x - 1])) + 1;
                    }
                } else {
                    squareLength[x] = 0;  // !!! clear cached value
                }

                topLeftLength = temp;

                maxSquareLength = Math.max(maxSquareLength, squareLength[x]);
            }
        }

        return maxSquareLength * maxSquareLength;
    }
}