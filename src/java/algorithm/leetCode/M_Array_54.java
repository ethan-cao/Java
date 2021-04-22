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

        System.out.println(Arrays.toString(spiralOrder0(matrix).toArray()));
        //  [1,2,3,6,9,8,7,4,5]

        int[][] matrix1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        System.out.println(Arrays.toString(spiralOrder0(matrix1).toArray()));
        // [1,2,3,4,8,12,11,10,9,5,6,7]
    }

    // 0ms
    public static List<Integer> spiralOrder0(int[][] matrix) {
        List<Integer> nums = new ArrayList<>();

        final int M = matrix.length;
        final int N = matrix[0].length;
        boolean[][] isCellVisited = new boolean[M][N];
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};  // !!! x is at idx 1, y is at idx 0
        int directionIdx = 0;

        int x = 0;
        int y = 0;

        while (nums.size() < M * N) {
            int num = matrix[y][x];  // !!! (x, y) -> [y, x]
            nums.add(num);
            isCellVisited[y][x] = true;

            int[] direction = directions[directionIdx];
            int nextX = x + direction[1];
            int nextY = y + direction[0];

            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !isCellVisited[nextY][nextX]) {
                // if next position is within the bounds of the matrix and unvisited, then it becomes our next position;
                y = nextY;
                x = nextX;
            } else {
                // otherwise, next position is the one after performing a clockwise turn.
                directionIdx = (directionIdx + 1) % 4;
                direction = directions[directionIdx];
                y += direction[0];
                x += direction[1];
            }
        }

        return nums;
    }

    // Time : O(N)), Space : O(N)
    // 0ms
    public static List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> nums = new ArrayList<>();

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // move towards right
            for (int col = left; col <= right; ++col) {
                nums.add(matrix[top][col]);
            }
            top++;

            // move towards bottom
            for (int row = top; row <= bottom; ++row) {
                nums.add(matrix[row][right]);
            }
            right--;

            // move towards top
            // since top changed, add extra check
            for (int col = right; col >= left && top <= bottom; --col) {
                nums.add(matrix[bottom][col]);
            }
            bottom--;

            // move towards top
            // since right changed, add extra check
            for (int row = bottom; row >= top && left <= right; --row) {
                nums.add(matrix[row][left]);
            }
            left++;
        }

        return nums;
    }


    // 0ms
    // not easy to get it correct in interview
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> nums = new ArrayList<>();

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        // !!! start from (-1, 0)
        int x = -1;
        int y = 0;

        while (left <= right && top <= bottom) {

            x++;
            while (x <= right) {
                nums.add(matrix[y][x]);  // [x, y] -> matrix[y][x]
                x++;
            }
            x--;
            top++;

            y++;
            while (y <= bottom && left <= right && top <= bottom) {
                nums.add(matrix[y][x]);
                y++;
            }
            y--;
            right--;

            x--;
            while (x >= left && left <= right && top <= bottom) {
                nums.add(matrix[y][x]);
                x--;
            }
            x++;
            bottom--;

            y--;
            while (y >= top && left <= right && top <= bottom) {
                nums.add(matrix[y][x]);
                y--;
            }
            y++;
            left++;
        }

        return nums;
    }
}