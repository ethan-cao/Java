package algorithm.leetCode;

/*
In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if
and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally
(ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.
If such a path does not exist, return -1.

1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1

### Example
https://leetcode.com/problems/shortest-path-in-binary-matrix/
[[0,1],[1,0]] -> 2
[[0,0,0],[1,1,0],[1,1,0]] -> 4

*/

import java.util.*;

public class M_BFS_Array_1091 {

    public static void main(String... args) {
        System.out.println(shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}}));  // 2
        System.out.println(shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}})); // 4
    }

    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {-1, 1}, {-1, -1}, {1, 1}};
    private static final int EMPTY = 0;
    private static final int BLOCKED = 1;

    // BFS, 15s
    // Time O(N*N), Space: O(N^N)
    public static int shortestPathBinaryMatrix(int[][] grid) {
        final int N = grid.length;
        final int start = 0;
        final int end = N - 1;

        if (grid[start][start] == BLOCKED || grid[end][end] == BLOCKED) {
            return -1;
        }

        int shortestPath = 0;  // !!! initialized with 0

        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N]; // alternative, use -1 to mark visited

        queue.offer(new int[]{start, start});
        visited[start][start] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                int[] position = queue.poll();
                int y = position[0];
                int x = position[1];

                if (y == end && x == end) {
                    return shortestPath + 1;
                }

                for (int[] direction : DIRECTIONS) {
                    int nextY = y + direction[0];
                    int nextX = x + direction[1];

                    if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N ) {
                        continue;
                    }

                    if (grid[nextY][nextX] == BLOCKED || visited[nextY][nextX]) {
                        continue;
                    }

                    queue.offer(new int[]{nextY, nextX});
                    visited[nextY][nextX] = true;
                }
            }

            shortestPath++;
        }

        return -1;
    }

    // DFS requires trying every possible path to the end
    // which needs to mark a cell as unvisited after recurring the neighbors (after the for-loop)
    // doing this in this problem would lead to TLE.
    // 'shortest' in path problem, dfs should be out of consideration.
    // Think about what djkstra's algo solves and remind yourself whether it is bfs or dfs


    // A* search
    // https://leetcode.com/problems/shortest-path-in-binary-matrix/discuss/313347/A*-search-in-Python
}
