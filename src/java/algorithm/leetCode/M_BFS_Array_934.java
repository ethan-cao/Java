package algorithm.leetCode;

/*
In a given 2D binary array A, there are two islands.
(An island is a 4-directionally connected group of 1s not connected to any other 1s.)
Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

1 <= A.length = A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1

### Example
[[0,1],[1,0]] -> 1
[[0,1,0],[0,0,0],[0,0,1]] -> 2
[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]] -> 1

*/

import java.util.*;

public class M_BFS_Array_934 {

    public static void main(String... args) {
        System.out.println(shortestBridge(new int[][]{{0, 1}, {1, 0}})); // 1
    }

    private static final int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int LAND = 1;
    private static final int WATER = 0;
    private static final int VISITED = -1;

    // BFS, 15ms
    // Time O(rows * cols), Space: O(rows * cols)
    public static int shortestBridge(int[][] A) {
        Deque<int[]> island = findIsland(A);
        return bridgeIslands(A, island);
    }

    private static Deque<int[]> findIsland(int[][] matrix) {
        final int M = matrix.length;
        final int N = matrix[0].length;

        Deque<int[]> island = new ArrayDeque<>();

        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < N; ++x) {
                if (matrix[y][x] == LAND) {
                    markIsland(matrix, y, x, island);
                    return island;
                }
            }
        }

        return island;
    }

    private static void markIsland(int[][] matrix, int y, int x, Deque<int[]> island) {
        if (y < 0 || y >= matrix.length || x < 0 || x >= matrix[0].length) {
            return;
        }

        if (matrix[y][x] != 1) {
            return;
        }

        island.offer(new int[]{y, x});
        matrix[y][x] = VISITED;   // if we dont want change input, use visited[][] instead

        for (int[] direction : directions) {
            int nextY = y + direction[0];
            int nextX = x + direction[1];

            markIsland(matrix, nextY, nextX, island);
        }
    }

    private static int bridgeIslands(int[][] matrix, Deque<int[]> island) {
        int bridgeCount = 0;

        final int M = matrix.length;
        final int N = matrix[0].length;

        while (!island.isEmpty()) {
            int size = island.size();

            for (int i = 0; i < size; ++i) {
                int[] land = island.poll();

                for (int[] direction : directions) {
                    int nextY = land[0] + direction[0];
                    int nextX = land[1] + direction[1];

                    if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) {
                        continue;
                    }

                    if (matrix[nextY][nextX] == VISITED) {
                        continue;
                    }

                    // when two islands connects, we have the bridgeCount
                    if (matrix[nextY][nextX] == LAND) {
                        return bridgeCount;
                    }

                    matrix[nextY][nextX] = VISITED;
                    island.offer(new int[]{nextY, nextX});
                }
            }

            bridgeCount++;
        }

        return -1;
    }

}