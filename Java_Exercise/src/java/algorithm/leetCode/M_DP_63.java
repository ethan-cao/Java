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

(Above is a 7 x 3 grid)


### Example
Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right


Related :  64, 63
*/

public class M_DP_63 {

    public static void main(String... args) {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid)); // 2

    }

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
}
