package algorithm.leetCode;

/*
Given a positive integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.

### Example
Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
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
        int counter = 0;

        int[][] vectors = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int direction = 0;

        int row = 0;
        int column = 0;

        while (counter < n * n) {
            matrix[row][column] = counter + 1;
            counter++;

            int[] vector = vectors[direction];
            int nextRow = row + vector[0];
            int nextColumn = column + vector[1];

            if (nextRow >= 0 && nextRow < n && nextColumn >= 0 && nextColumn < n && matrix[nextRow][nextColumn] == 0) {
                row = nextRow;
                column = nextColumn;
            } else {
                direction = (direction + 1) % 4;
                vector = vectors[direction];
                row = row + vector[0];
                column = column + vector[1];
            }
        }

        return matrix;
    }

}