package algorithm.leetCode;

/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish').
m <= 100, n<=100

Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and empty space is marked as 1 and 0 respectively in the grid.

[start]  *  *  *  *  *  *
      *  *  *  *  *  *  *
      *  *  *  *  *  *  [Finish]

### Example
[ [0,0,0],
  [0,1,0],
  [0,0,0] ]   -> 2
*/

public class M_DP_63 {

    public static void main(String... args) {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println(uniquePathsWithObstacles1(obstacleGrid)); // 2

        int[][] obstacleGrid1 = {
                {1, 0}
        };
        System.out.println(uniquePathsWithObstacles1(obstacleGrid1)); // 0

        int[][] obstacleGrid2 = {
                {0, 0}
        };
        System.out.println(uniquePathsWithObstacles1(obstacleGrid2)); // 1
    }

    // DP, recursive, Too slow
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0][0] == 1) {
            return 0;
        }

        return getPath(obstacleGrid, 0, 0);
    }

    private static int getPath(int[][] grid, int currentX, int currentY) {
        if (currentX == grid.length - 1 && currentY == grid[0].length - 1) {
            return grid[currentX][currentY] == 1 ? 0 : 1;
        }

        int pathCount = 0;

        if (currentX + 1 < grid.length) {
            if (grid[currentX + 1][currentY] == 0) {
                pathCount += getPath(grid, currentX + 1, currentY);
            }
        }

        if (currentY + 1 < grid[0].length) {
            if (grid[currentX][currentY + 1] == 0) {
                pathCount += getPath(grid, currentX, currentY + 1);
            }
        }

        return pathCount;
    }

    // DP, iterative, 0ms
    public static int uniquePathsWithObstacles1(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;
        final int OBSTACLE = 1;
        final int SPACE = 0;

        int[][] result = new int[M][N];

        for (int y = 0; y < M; ++y) {
            if (grid[y][0] == OBSTACLE) {
                break;
            }

            result[y][0] = 1;
        }

        for (int x = 0; x < N; ++x) {
            if (grid[0][x] == OBSTACLE) {
                break;
            }

            result[0][x] = 1;
        }

        for (int y = 1; y < M; ++y) {
            for (int x = 1; x < N; ++x) {
                int cell = grid[y][x];

                if (cell == OBSTACLE) {
                    continue;
                }

                result[y][x] = result[y - 1][x] + result[y][x - 1];
            }
        }

        return result[M - 1][N - 1];
    }

    // DP, iterative, condensed space, 0ms
    public static int uniquePathsWithObstacles2(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;
        final int OBSTACLE = 1;
        final int SPACE = 0;

        int[] result = new int[N];

        for (int x = 0; x < N; ++x) {
            if (grid[0][x] == OBSTACLE) {
                break;
            }

            result[x] = 1;
        }

        for (int y = 1; y < M; ++y) {
            for (int x = 0; x < N; ++x) {
                int cell = grid[y][x];

                if (x == 0) {
                    if (result[x] != 0) { // !!!
                        result[x] = cell == OBSTACLE ? 0 : 1;
                    }
                } else {
                    if (cell == OBSTACLE) {
                        result[x] = 0;
                    } else {
                        result[x] = result[x - 1] + result[x];
                    }
                }
            }
        }

        return result[N - 1];
    }

}

