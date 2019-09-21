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

public class M_DP_Array_64 {

    public static void main(String... args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minPathSum1(grid)); // 7

        int[][] grid1 = {
                {12, 3, 1},
                {1, 5, 1}
        };
        System.out.println(minPathSum1(grid1)); // 17
    }

    // Recursive, use tree to analyze, DFS
    // get sum of each path towards each leaf
    public static int minPathSum(int[][] grid) {
        return getPathSum(grid, 0, 0);
    }

    // return minimal sum from grid[i][j] to all its leaves
    private static int getPathSum(int[][] grid, int i, int j) {
        int current = grid[i][j];

        // base case
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
        int m = grid.length;
        int n = grid[0].length;

        int[][] minSum = new int[m][n];
        minSum[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) {
                    minSum[i][j] = minSum[i][j - 1] + grid[i][j];
                }

                if (j == 0 && i > 0) {
                    minSum[i][j] = minSum[i - 1][j] + grid[i][j];
                }

                if (i > 0 && j > 0) {
                    minSum[i][j] = Math.min(minSum[i][j - 1], minSum[i - 1][j]) + grid[i][j];
                }
            }
        }

        return minSum[m - 1][n - 1];
    }

    // Iterative with 1-d array
    public static int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] minSum = new int[n];
        minSum[0] = grid[0][0];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {

                if (i == 0 && j > 0) {
                    minSum[j] = minSum[j - 1] + grid[i][j];
                }

                if (j == 0 && i > 0) {
                    minSum[j] = minSum[j] + grid[i][j];
                }

                if (i > 0 && j > 0) {
                    minSum[j] = Math.min(minSum[j], minSum[j - 1]) + grid[i][j];
                }
            }
        }

        return minSum[n - 1];
    }
}
