package algorithm.leetCode;

/*
You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
DO NOT allocate another 2D matrix and do the rotation.

### Example
Given input matrix =
[  [1,2,3],
   [4,5,6],
   [7,8,9]  ],
rotate the input matrix in-place such that it becomes:
[  [7,4,1],
   [8,5,2],
   [9,6,3] ]

Given input matrix =
[ [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16] ],

rotate the input matrix in-place such that it becomes:
[ [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11] ]
*/


import java.util.Arrays;

public class M_Array_48 {

    public static void main(String... args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };

        int[][] matrix1 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        rotate1(matrix);

        for (int[] merge : matrix) {
            System.out.println(Arrays.toString(merge));
        }
    }

    // time : O(N^2)
    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int L = matrix.length;

        // transpose matrix : A[i][j] = A[j][i]
        for (int x = 0; x < L; ++x) {
            for (int y = 0; y <= x; ++y) {
                swap(matrix, x, y, y, x);
            }
        }

        // flip matrix horizontally
        // flip matrix vertically, for anticlockwise rotation,
        for (int x = 0; x < L; ++x) {
            for (int y = 0; y < L / 2; ++y) {
                swap(matrix, x, y, x, L - 1 - y);
            }
        }
    }

    private static void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    // https://leetcode.com/problems/rotate-image/discuss/18895/Clear-Java-solution/152227
    public static void rotate1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int L = matrix.length;

        for (int x = 0; x < L / 2; ++x) {
            for (int y = 0; y < L - 1 - x; ++y) {
                rotate(matrix, x, y);
            }
        }
    }

    private static void rotate(int[][] matrix, int x, int y) {
        int L = matrix.length;

        int temp = matrix[x][y];
        matrix[x][y] = matrix[L - 1 - y][x];
        matrix[L - 1 - y][x] = matrix[L - 1 - y][L - 1 - x];
        matrix[L - 1 - y][L - 1 - x] = matrix[y][L - 1 - x];
        matrix[y][L - 1 - x] = temp;
    }
}
