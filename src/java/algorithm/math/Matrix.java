package algorithm.math;

import java.util.Arrays;

public class Matrix {

    public static void main(String[] args) {
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

        int[][] matrix2 = {
                {1, 2, 3},
                {0, -6, 7},
        };

        int[][] matrix3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };

//        print(transpose(matrix2));
        print(multiply(matrix2, matrix3));
    }

    public static void print(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[][] transpose(int[][] matrix) {
        final int M = matrix.length;
        final int N = matrix[0].length;

        int[][] transposedMatrix = new int[N][M];

        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < N; ++x) {
                transposedMatrix[x][y] = matrix[y][x];
            }
        }

        return transposedMatrix;
    }

    public static int[][] multiply(int[][] matrixA, int[][] matrixB) {
        final int M = matrixA.length;
        final int S1 = matrixA[0].length;
        final int S2 = matrixB.length;
        final int N = matrixB[0].length;

        if (S1 != S2) {
            throw new IllegalArgumentException("Cannot multiply");
        }

        int[][] product = new int[M][N];

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                for (int k = 0; k < S1; ++k) {
                    product[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return product;
    }

}
