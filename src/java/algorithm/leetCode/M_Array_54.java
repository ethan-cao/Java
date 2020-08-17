package algorithm.leetCode;

/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

### Example
Input:
[ [ 1, 2, 3 ],
  [ 4, 5, 6 ],
  [ 7, 8, 9 ] ]
Output: [1,2,3,6,9,8,7,4,5]

Input:
[ [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12] ]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

*/

import java.util.*;

public class M_Array_54 {

    public static void main(String... args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };

        System.out.println(Arrays.toString(spiralOrder2(matrix).toArray()));
        //  [1,2,3,6,9,8,7,4,5]

        int[][] matrix1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        System.out.println(Arrays.toString(spiralOrder2(matrix1).toArray()));
        // [1,2,3,4,8,12,11,10,9,5,6,7]
    }

    // Time : O(N)), Space : O(N), N is the total number of elements
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return order;

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        while (left <= right && top <= bottom) {
            // move to right
            for (int column = left; column <= right; ++column) {
                order.add(matrix[top][column]);
            }
            top++;

            // move to bottom
            for (int row = top; row <= bottom; ++row) {
                order.add(matrix[row][right]);
            }
            right--;

            // move to left
            for (int column = right; column >= left && top <= bottom; --column) {
                order.add(matrix[bottom][column]);
            }
            bottom--;

            // move to top
            for (int row = bottom; row >= top && left <= right; --row) {
                order.add(matrix[row][left]);
            }
            left++;

        }

        return order;
    }

    // 0ms
    public static List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return order;

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int direction = 0;

        while (left <= right && top <= bottom) {
            switch (direction) {
                case 0: // move to right
                    for (int column = left; column <= right; ++column) {
                        order.add(matrix[top][column]);
                    }
                    top++;
                    break;
                case 1: // move to bottom
                    for (int row = top; row <= bottom; ++row) {
                        order.add(matrix[row][right]);
                    }
                    right--;
                    break;
                case 2: // move to left
                    for (int column = right; column >= left; --column) {
                        order.add(matrix[bottom][column]);
                    }
                    bottom--;
                    break;
                case 3: // move to top
                    for (int row = bottom; row >= top; --row) {
                        order.add(matrix[row][left]);
                    }
                    left++;
                    break;
                default:
                    break;
            }

            direction = (direction + 1) % 4;
        }

        return order;
    }

    // 0ms
    public static List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return order;

        int M = matrix.length;
        int N = matrix[0].length;
        boolean[][] visited = new boolean[M][N];

        int[][] vectors = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};  // towards right, towards bottom, towards left, towards top
        int direction = 0;

        int row = 0;
        int column = 0;

        while (order.size() < M * N) {
            order.add(matrix[row][column]);
            visited[row][column] = true;

            int[] vector = vectors[direction];
            int nextRow = row + vector[0];
            int nextColumn = column + vector[1];

            if (nextRow >= 0 && nextRow < M && nextColumn >= 0 && nextColumn < N && !visited[nextRow][nextColumn]) {
                // if next position is within the bounds of the matrix and unvisited, then it becomes our next position;
                row = nextRow;
                column = nextColumn;
            } else {
                // otherwise, next position is the one after performing a clockwise turn.
                direction = (direction + 1) % 4;
                vector = vectors[direction];
                row += vector[0];
                column += vector[1];
            }
        }

        return order;
    }

}