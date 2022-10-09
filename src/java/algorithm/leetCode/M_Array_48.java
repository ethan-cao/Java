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

        rotate_antiClock(matrix1);

        for (int[] merge : matrix1) {
            System.out.println(Arrays.toString(merge));
        }
    }

    // Time: O(N^2), 0ms
    public static void rotate1(int[][] matrix) {
        final int N = matrix.length;

        // rotate ring by ring
        for (int i = 0; i < N / 2; ++i) {
            for (int j = i; j < N - 1 - i; ++j) {
                rotate(matrix, i, j);
            }
        }
    }

    private static void rotate(int[][] matrix, int i, int j) {
        final int N = matrix.length;

        // infer the 3 location based on [i, j]
        int temp = matrix[i][j];
        matrix[i][j] = matrix[N - 1 - j][i];
        matrix[N - 1 - j][i] = matrix[N - 1 - i][N - 1 - j];
        matrix[N - 1 - i][N - 1 - j] = matrix[j][N - 1 - i];
        matrix[j][N - 1 - i] = temp;
    }

    // Time: O(N^2), 0ms
    // works for N*N matrix
    public static void rotate(int[][] matrix) {
        final int N = matrix.length;

        // transpose
        for (int i = 0; i < N; ++i) {
            for (int j = i; j < N; ++j) {
                swap(matrix, i, j, j, i);
            }
        }

        // reverse each row
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N / 2; ++j) {
                swap(matrix, i, j, i, N - j - 1);
            }
        }
    }

    // Time: O(N^2)
    // works for N*N matrix
    public static void rotate_antiClock(int[][] matrix) {
        final int N = matrix.length;

        // reverse each row
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N / 2; ++j) {
                swap(matrix, i, j, i, N - j - 1);
            }
        }

        // transpose
        for (int i = 0; i < N; ++i) {
            for (int j = i; j < N; ++j) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    private static void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

}