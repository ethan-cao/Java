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


    // DFS requires trying every possible path to the end
    // which needs to mark a cell as unvisited after recurring the neighbors (after the for-loop)
    // doing this in this problem would lead to TLE.
    // 'shortest' in path problem, dfs should be out of consideration.
    // Think about what djkstra's algo solves and remind yourself whether it is bfs or dfs

    // BFS, 16ms
    // Time O(), Space: O()
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int N = grid.length;
        int START = grid[0][0];
        int END = grid[N - 1][N - 1];

        if (START == 1 || END == 1) {
            return -1;
        }

        int shortestPath = 0;
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {-1, 1}, {-1, -1}, {1, 1}};
        boolean[][] visited = new boolean[N][N];
        Deque<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0});  // start (0, 0)
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                if (x == N - 1 && y == N - 1) {
                    return shortestPath + 1;
                }

                for (int[] direction : directions) {
                    int nextX = x + direction[0];
                    int nextY = y + direction[1];

                    if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextX][nextY] || grid[nextX][nextY] == 1) {
                        continue;
                    }

                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }

            shortestPath++;
        }

        return -1;
    }

}
