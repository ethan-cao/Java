package algorithm.leetCode;

/*
Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

### Example
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

Related : 62
*/

public class M_DP_64 {

    public static void main(String... args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minPathSum(grid)); // 7

        int[][] grid1 = {
                {12, 3, 1},
                {1, 5, 1}
        };
        System.out.println(minPathSum(grid1)); // 17
    }

    public static int minPathSum(int[][] grid) {
        return getPathSum(grid, 0, 0);
    }

    private static int getPathSum(int[][] grid, int i, int j) {
        int current = grid[i][j];

        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return current;
        }

        if (i == grid.length - 1) {
            return current + getPathSum(grid, i, j + 1);
        }

        if (j == grid[0].length - 1) {
            return current + getPathSum(grid, i + 1, j);
        }

        return current + Math.min(getPathSum(grid, i + 1, j), getPathSum(grid, i, j + 1));
    }

    // Iterative
    // Suppose the minimum path sum of arriving at point (i, j) is S[i][j],
    // then the state equation is S[i][j] = min(S[i - 1][j], S[i][j - 1]) + grid[i][j].
    public static int minPathSum1(int[][] grid) {
        // pathSum from (0,0) to (i, j)
        int[][] pathSum = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                pathSum[i][j] = grid[i][j];
            }
        }

        return 1;
    }
}
