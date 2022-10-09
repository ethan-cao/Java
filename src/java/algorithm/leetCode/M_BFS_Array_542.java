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

    // BFS, 15ms
    // Time O(M*N), Space: O()
    public int[][] updateMatrix1(int[][] matrix) {
        final int M = matrix.length;
        final int N = matrix[0].length;
        final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int[][] distances = new int[M][N];
        boolean[][] hasDistance = new boolean[M][N];

        Deque<int[]> queue = new ArrayDeque<>();

        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < N; ++x) {
                if (matrix[y][x] == 0) {
                    queue.offer(new int[]{y, x});
                    hasDistance[y][x] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                int[] position = queue.poll();
                int y = position[0];
                int x = position[1];

                for (int[] direction : directions) {
                    int nextY = y + direction[0];
                    int nextX = x + direction[1];

                    if (nextY < 0 || nextY >= M || nextX < 0 || nextX >= N) {
                        continue;
                    }

                    if (hasDistance[nextY][nextX]) {
                        continue;
                    }

                    // calculate distance to any valid adjacent cells of a cell that already has distance
                    distances[nextY][nextX] = distances[y][x] + 1;

                    queue.offer(new int[]{nextY, nextX});
                    hasDistance[nextY][nextX] = true;
                }
            }
        }

        return distances;
    }

    // DP, 5ms
    // Time O(M*N), Space: O()
    public int[][] updateMatrix(int[][] matrix) {
        final int M = matrix.length;
        final int N = matrix[0].length;
        final int MAX = (M - 1) + (N - 1); // the maximal possible distance, !!! cannot use Integer.MAX_VALUE, overflow
        final int ZERO = 0;

        int[][] distances = new int[M][N];

        // scan from top-left and bottom-right, just like BFS

        // top -> bottom, left -> right
        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < N; ++x) {
                int cell = matrix[y][x];

                if (cell != ZERO) {
                    int topCellDistance = y - 1 >= 0 ? distances[y - 1][x] : MAX;
                    int leftCellDistance = x - 1 >= 0 ? distances[y][x - 1] : MAX;

                    distances[y][x] = Math.min(topCellDistance, leftCellDistance) + 1;
                }
            }
        }

        // bottom -> top, right -> left
        for (int y = M - 1; y >= 0; --y) {
            for (int x = N - 1; x >= 0; --x) {
                int cell = matrix[y][x];

                if (cell != ZERO) {
                    int downCellDistance = y + 1 <= M - 1 ? distances[y + 1][x] : MAX;
                    int rightCellDistance = x + 1 <= N - 1 ? distances[y][x + 1] : MAX;

                    int distance = Math.min(downCellDistance, rightCellDistance) + 1;

                    distances[y][x] = Math.min(distances[y][x], distance);
                }
            }
        }

        return distances;
    }

}