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

        rotate1(matrix1);

        for (int[] merge : matrix1) {
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
        for (int i = 0; i < L; ++i) {
            for (int j = 0; j <= i; ++j) {
                swap(matrix, i, j, j, i);
            }
        }

        // flip matrix horizontally
        // flip matrix vertically, for anticlockwise rotation,
        for (int i = 0; i < L; ++i) {
            for (int j = 0; j < L / 2; ++j) {
                swap(matrix, i, j, i, L - 1 - j);
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

        // iterate half row
        for (int i = 0; i < L / 2; ++i) {
            // iterate half column
            for (int j = i; j < L - 1 - i; ++j) {
                rotate(matrix, i, j);
            }
        }
    }

    // !!! Practice repeatedly to have better 2D array comprehension
    private static void rotate(int[][] matrix, int i, int j) {
        int L = matrix.length;

        int temp = matrix[i][j];
        matrix[i][j] = matrix[L - 1 - j][i];
        matrix[L - 1 - j][i] = matrix[L - 1 - i][L - 1 - j];
        matrix[L - 1 - i][L - 1 - j] = matrix[j][L - 1 - i];
        matrix[j][L - 1 - i] = temp;
    }

}