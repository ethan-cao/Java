package algorithm.leetCode;

/*
Given a positive integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.

### Example
Input: 3
Output:
[ [ 1, 2, 3 ],
  [ 8, 9, 4 ],
  [ 7, 6, 5 ]  ]

*/

import java.util.Arrays;

public class M_Array_59 {

    public static void main(String... args) {
        int[][] matrix1 = generateMatrix(2);
        for (int[] m : matrix1) {
            System.out.println(Arrays.toString(m));
        }

        int[][] matrix2 = generateMatrix(3);
        for (int[] m : matrix2) {
            System.out.println(Arrays.toString(m));
        }

        int[][] matrix3 = generateMatrix(4);
        for (int[] m : matrix3) {
            System.out.println(Arrays.toString(m));
        }

    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int i = 1;
        int row = 0;
        int col = 0;

        // left, bottom, right, top
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIdx = 0;

        while (i <= n * n) {
            matrix[row][col] = i;

            int[] direction = directions[directionIdx];
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n && matrix[nextRow][nextCol] == 0) {
                row = nextRow;
                col = nextCol;
            } else {
                directionIdx = (directionIdx + 1) % 4;
                direction = directions[directionIdx];
                row = row + direction[0];
                col = col + direction[1];
            }

            i++;
        }

        return matrix;
    }

}