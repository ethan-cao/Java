package algorithm.leetCode;

/*
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1. (Manhattan distance)

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

        for (int[] distance : distances) {
            Arrays.fill(distance, Integer.MAX_VALUE);
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
        final int M = matrix.length;
        final int N = M == 0 ? 0 : matrix[0].length;
        int[][] distances = new int[M][N];

//        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] hasDistance = new boolean[M][N];

        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < N; ++x) {
                if (matrix[y][x] == 0) {
                    queue.offer(new int[]{y, x});
                    hasDistance[y][x] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int y = position[0];
            int x = position[1];

            for (int[] direction : directions) {
                int nextY = y + direction[0];
                int nextX = x + direction[1];

                if (nextY < 0 || nextY >= M || nextX < 0 || nextX >= N || hasDistance[nextY][nextX]) {
                    continue;
                }

                // calculate distance to any valid adjacent cells of a cell that already has distance
                distances[nextY][nextX] = distances[y][x] + 1;

                queue.offer(new int[]{nextY, nextX});
                hasDistance[nextY][nextX] = true;
            }
        }

        return distances;
    }

}