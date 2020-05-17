package algorithm.leetCode;

/*
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1.

the number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.

### Example
[[0,0,0],
 [0,1,0],
 [0,0,0]]
->
[[0,0,0],
 [0,1,0],
 [0,0,0]]


[[0,0,0],
 [0,1,0],
 [1,1,1]]
->
[[0,0,0],
 [0,1,0],
 [1,2,1]]

*/

import java.util.*;

public class M_BFS_Array_542 {

    // DP, 5ms
    public int[][] updateMatrix(int[][] matrix) {
        int M = matrix.length;
        int N = M == 0 ? 0 : matrix[0].length;
        int range = M * N;
        int[][] distances = new int[M][N];

        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        // Left -> Right, Top down scan
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    distances[i][j] = 0;
                } else {
                    int upCell = (i > 0) ? distances[i - 1][j] : range;
                    int leftCell = (j > 0) ? distances[i][j - 1] : range;
                    distances[i][j] = Math.min(upCell, leftCell) + 1;
                }
            }
        }

        // Right -> Left, Bottom up scan
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    distances[i][j] = 0;
                } else {
                    int downCell = (i < matrix.length - 1) ? distances[i + 1][j] : range;
                    int rightCell = (j < matrix[0].length - 1) ? distances[i][j + 1] : range;
                    distances[i][j] = Math.min(Math.min(downCell, rightCell) + 1, distances[i][j]);
                }
            }
        }
        return distances;
    }

    // BFS, 17ms
    // Time O(), Space: O()
    public int[][] updateMatrix1(int[][] matrix) {
        int M = matrix.length;
        int N = M == 0 ? 0 : matrix[0].length;
        int[][] distances = new int[M][N];
        boolean[][] visited = new boolean[M][N];
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Deque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // BFS starting from each 0 cell
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();

            for (int[] direction : directions) {
                int newX = cell[0] + direction[0];
                int newY = cell[1] + direction[1];

                if (newX < 0 || newX >= M || newY < 0 || newY >= N || visited[newX][newY]) {
                    continue;
                }

                visited[newX][newY] = true;
                distances[newX][newY] = distances[cell[0]][cell[1]] + 1;
                queue.offer(new int[]{newX, newY});
            }
        }

        return distances;
    }

}