package algorithm.leetCode;

/*
Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

### Example
Consider the following matrix:
[ [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30] ]
Given target = 5, return true.
Given target = 20, return false.

*/

import java.util.Arrays;

public class M_BinarySearch_Array_240 {

    public static void main(String... args) {
        int[][] nums = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        System.out.println(searchMatrix(nums, 5));   // T
        System.out.println(searchMatrix(nums, 20));  // F
    }

    // 4ms Binary search
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }

        // start from top right, this is middle value for binary search in the row and column
        int row = 0;
        int column = matrix[0].length - 1;

        while (row < matrix.length && column >= 0) {
            if (target == matrix[row][column]) {
                return true;
            }

            if (target > matrix[row][column]) {
                row++;
            } else {
                column--;
            }
        }

        return false;
    }
}
