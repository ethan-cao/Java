package algorithm.leetCode;

/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Follow up: Could you devise a constant space solution?

### Example
Input:
[  [1,1,1],
   [1,0,1],
   [1,1,1] ]
Output:
[  [1,0,1],
   [0,0,0],
   [1,0,1] ]

Input:
[  [0,1,2,0],
   [3,4,5,2],
   [1,3,1,5] ]
Output:
[   [0,0,0,0],
    [0,4,5,0],
    [0,3,1,0] ]

]*/

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    public static void setZeroes(int[][] matrix) {
        boolean cleanFirstRow = false;
        boolean cleanFirstColumn = false;

        // mark row and column to be cleaned
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {

                // !!! this will not work, since it will execute even when i is not 0,
                // and if the previous row already mark it as 0, cleanFirstRow will be 0
                // matrix[0][j] == 0    is not     (matrix[i][j] == 0) && (i ==0)
//                if (matrix[0][j] == 0){
//                    cleanFirstRow = true;
//                }

                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        cleanFirstRow = true;
                    }

                    if (j == 0) {
                        cleanFirstColumn = true;
                    }

                    // use 1st row and 1st column to indicate the that row / column should be cleaned
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // set zero, except 1st row and 1st column
        for (int i = 1; i < matrix.length; ++i) {
            for (int j = 1; j < matrix[0].length; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (cleanFirstRow) {
            for (int column = 0; column < matrix[0].length; ++column) {
                matrix[0][column] = 0;
            }
        }

        if (cleanFirstColumn) {
            for (int row = 0; row < matrix.length; ++row) {
                matrix[row][0] = 0;
            }
        }
    }
}

