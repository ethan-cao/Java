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
    public static void setZeroes(int[][] matrix) {
        boolean isFirstRowClean = false;
        boolean isFirstColClean = false;

        int ROW_SIZE = matrix.length;
        int COL_SIZE = matrix[0].length;

        for (int row = 0; row < ROW_SIZE; ++row) {
            for (int col = 0; col < COL_SIZE; ++col) {
                if (matrix[row][col] == 0) {

                    isFirstRowClean = row == 0 ? true : isFirstRowClean;
                    isFirstColClean = col == 0 ? true : isFirstColClean;

                    // use 1st row and 1st column to indicate the that row / column should be cleaned
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }
        // set zero, except 1st row and 1st column
        for (int row = 1; row < ROW_SIZE; ++row) {
            for (int col = 1; col < COL_SIZE; ++col) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }

        if (isFirstRowClean) {
            for (int col = 0; col < COL_SIZE; ++col) {
                matrix[0][col] = 0;
            }
        }

        if (isFirstColClean) {
            for (int row = 0; row < ROW_SIZE; ++row) {
                matrix[row][0] = 0;
            }
        }
    }
}

