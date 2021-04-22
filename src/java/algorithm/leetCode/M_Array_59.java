package algorithm.leetCode;

/*
Given a positive integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.

### Example
3 -> [ [ 1, 2, 3 ],
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

        final int N = matrix.length;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};  // !!! x is at idx 1, y is at idx 0
        int directionIdx = 0;
        int num = 1;
        int x = 0;
        int y = 0;

        while (num <= N * N) {
            matrix[y][x] = num;
            num++;

            int[] direction = directions[directionIdx];
            int nextX = x + direction[1];
            int nextY = y + direction[0];

            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && matrix[nextY][nextX] == 0) {
                x = nextX;
                y = nextY;
            } else {
                directionIdx = (directionIdx + 1) % 4;
                direction = directions[directionIdx];
                x += direction[1];
                y += direction[0];
            }
        }

        return matrix;
    }

    // 0ms
    public int[][] generateMatrix1(int n) {
        int[][] matrix = new int[n][n];

        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;

        int num = 1;
        int x = -1;
        int y = 0;

        while (num <= n * n && left <= right && top <= bottom) {

            x++;
            while (x <= right) {
                matrix[y][x] = num;
                num++;
                x++;
            }
            top++;
            x--;

            y++;
            while (y <= bottom) {
                matrix[y][x] = num;
                num++;
                y++;
            }
            right--;
            y--;

            x--;
            while (x >= left) {
                matrix[y][x] = num;
                num++;
                x--;
            }
            bottom--;
            x++;

            y--;
            while (y >= top) {
                matrix[y][x] = num;
                num++;
                y--;
            }
            left++;
            y++;

        }

        return matrix;
    }

}