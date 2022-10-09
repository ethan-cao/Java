package algorithm.leetCode;

/*
Given a non-empty 2D array grid of 0's and 1's,
an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0)
The length of each dimension in the given grid does not exceed 50.

### Example
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]] -> 6

 [[0,0,0,0,0,0,0,0]] -> 0
*/

import java.util.*;

public class M_DFS_695 {

    public static void main(String[] args) {
        System.out.println(maxAreaOfIsland(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        })); // 6
    }

    // DFS, 2ms
    public static int maxAreaOfIsland(int[][] grid) {
        int maxIslandArea = 0;

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return maxIslandArea;
        }

        final int M = grid.length;
        final int N = grid[0].length;
        boolean[][] visited = new boolean[M][N];

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {

                if (grid[i][j] == 0 || visited[i][j]) {
                    continue;
                }

                maxIslandArea = Math.max(maxIslandArea, getArea(grid, i, j, visited));
            }
        }

        return maxIslandArea;
    }

    public static int getArea(int[][] grid, int x, int y, boolean[][] visited) {
        final int M = grid.length;
        final int N = grid[0].length;

        if (x < 0 || x >= M || y < 0 || y >= N) {
            return 0;
        }

        if (visited[x][y]) {
            return 0;
        }

        visited[x][y] = true;

        if (grid[x][y] == 0) {
            return 0;
        }

        return 1 +
                getArea(grid, x + 1, y, visited) +
                getArea(grid, x - 1, y, visited) +
                getArea(grid, x, y + 1, visited) +
                getArea(grid, x, y - 1, visited);
    }

    // TODO BFS
    public static int maxAreaOfIsland1(int[][] grid) {
        return 1;
    }
}