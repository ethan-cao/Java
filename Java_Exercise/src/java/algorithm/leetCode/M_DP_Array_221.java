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
*/


public class M_DP_Array_221 {

    public static void main(String... args) {
        char[][] matrix = {
                {1, 0, 1, 0, 0}
        };
        System.out.println(maximalSquare(matrix));  // 1

        char[][] matrix1 = {
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };
        System.out.println(maximalSquare(matrix1));  // 4
    }

    // DP
    public static int maximalSquare(char[][] matrix) {
        int maxSquare = 1;

        for (int i = 1; i < matrix.length; ++i) {
//            int square = getSquare(matrix, i);
//            maxSquare = Math.max(maxSquare, square);
        }

        return maxSquare;
    }


}