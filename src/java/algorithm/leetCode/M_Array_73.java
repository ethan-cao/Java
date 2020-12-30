package algorithm.leetCode;

/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
Follow up: Could you devise a constant space solution?

### Example
[  [1,1,1],
   [1,0,1],
   [1,1,1] ]
->
[  [1,0,1],
   [0,0,0],
   [1,0,1] ]

[  [0,1,2,0],
   [3,4,5,2],
   [1,3,1,5] ]
->
[   [0,0,0,0],
    [0,4,5,0],
    [0,3,1,0] ]

]*/

import java.util.*;

public class M_Array_73 {

    public static void main(String... args) {
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        setZeroes(matrix);
        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
    }

    // 1ms
    // Time: O(N^2)  Space: O(N)
    public static void setZeroes(int[][] matrix) {
        boolean isFirstRowClean = false;
        boolean isFirstColClean = false;

        int M = matrix.length;
        int N = matrix[0].length;

        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < N; ++x) {
                if (matrix[y][x] == 0) {

                    isFirstRowClean = y == 0 || isFirstRowClean;
                    isFirstColClean = x == 0 || isFirstColClean;

                    // use 1st row and 1st column to indicate the that row / column should be cleaned
                    matrix[0][x] = 0;
                    matrix[y][0] = 0;
                }
            }
        }

        // set zero, except 1st row and 1st column
        for (int y = 1; y < M; ++y) {
            for (int x = 1; x < N; ++x) {
                if (matrix[y][0] == 0 || matrix[0][x] == 0) {
                    matrix[y][x] = 0;
                }
            }
        }

        if (isFirstRowClean) {
            for (int x = 0; x < N; ++x) {
                matrix[0][x] = 0;
            }
        }

        if (isFirstColClean) {
            for (int y = 0; y < M; ++y) {
                matrix[y][0] = 0;
            }
        }
    }
}

