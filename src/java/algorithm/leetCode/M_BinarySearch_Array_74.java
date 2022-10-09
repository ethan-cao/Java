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

public class M_BinarySearch_Array_74 {

    public static void main(String... args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        System.out.println(searchMatrix(matrix, 3)); // T
        System.out.println(searchMatrix(matrix, 13)); // F
    }

    // Binary search, 0ms
    // Time O(NlogN)
    public static boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        int N = matrix[0].length;

        int left = 0;
        int right = M * N - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int y = middle / N;
            int x = middle % N;
            int val = matrix[y][x];

            if (val == target) {
                return true;
            } else if (target > val) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return false;
    }

}