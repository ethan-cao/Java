package algorithm.leetCode;

/*
Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:
    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.

### Example
Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true


Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false

]*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class M_Array_74 {

    public static void main(String... args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        System.out.println(searchMatrix(matrix, 3)); // T
        System.out.println(searchMatrix(matrix, 13)); // F
    }

    // Binary search
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length ==0){
            return false;
        }

        int M = matrix.length;
        int N = matrix[0].length;

        int start = 0;
        int end = M * N - 1;

        while (start <= end) {
            int middle = start + (end - start) / 2;
            int middleRow = middle / N;
            int middleColumn = middle % N;
            int middleValue = matrix[middleRow][middleColumn];

            if (middleValue < target) {
                start = middle +1;
            } else if (middleValue > target) {
                end = middle - 1;
            } else {
                return true;
            }
        }

        return false;
    }

}