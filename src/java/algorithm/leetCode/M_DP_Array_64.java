package algorithm.leetCode;

/*
Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

### Example
[ [1,3,1],
  [1,5,1],
  [4,2,1] ]
-> 7, path: 1 → 3 → 1 → 1 → 1

*/

import java.util.Arrays;

public class M_DP_Array_64 {

    public static void main(String... args) {

        int[][] grid = {
                {3, 8, 6, 0, 5, 9, 9, 6, 3, 4, 0, 5, 7, 3, 9, 3},
                {0, 9, 2, 5, 5, 4, 9, 1, 4, 6, 9, 5, 6, 7, 3, 2},
                {8, 2, 2, 3, 3, 3, 1, 6, 9, 1, 1, 6, 6, 2, 1, 9},
                {1, 3, 6, 9, 9, 5, 0, 3, 4, 9, 1, 0, 9, 6, 2, 7},
                {8, 6, 2, 2, 1, 3, 0, 0, 7, 2, 7, 5, 4, 8, 4, 8},
                {4, 1, 9, 5, 8, 9, 9, 2, 0, 2, 5, 1, 8, 7, 0, 9},
                {6, 2, 1, 7, 8, 1, 8, 5, 5, 7, 0, 2, 5, 7, 2, 1},
                {8, 1, 7, 6, 2, 8, 1, 2, 2, 6, 4, 0, 5, 4, 1, 3},
                {9, 2, 1, 7, 6, 1, 4, 3, 8, 6, 5, 5, 3, 9, 7, 3},
                {0, 6, 0, 2, 4, 3, 7, 6, 1, 3, 8, 6, 9, 0, 0, 8},
                {4, 3, 7, 2, 4, 3, 6, 4, 0, 3, 9, 5, 3, 6, 9, 3},
                {2, 1, 8, 8, 4, 5, 6, 5, 8, 7, 3, 7, 7, 5, 8, 3},
                {0, 7, 6, 6, 1, 2, 0, 3, 5, 0, 8, 0, 8, 7, 4, 3},
                {0, 4, 3, 4, 9, 0, 1, 9, 7, 7, 8, 6, 4, 6, 9, 5},
                {6, 5, 1, 9, 9, 2, 2, 7, 4, 2, 7, 2, 2, 3, 7, 2},
                {7, 1, 9, 6, 1, 2, 7, 0, 9, 6, 6, 4, 4, 5, 1, 0},
                {3, 4, 9, 2, 8, 3, 1, 2, 6, 9, 7, 0, 2, 4, 2, 0},
                {5, 1, 8, 8, 4, 6, 8, 5, 2, 4, 1, 6, 2, 2, 9, 7}
        };

        System.out.println(minPathSum1(grid)); // 7

//        int[][] grid1 = {
//                {12, 3, 1},
//                {1, 5, 1}
//        };
//        System.out.println(minPathSum1(grid1)); // 17
    }

    // DP, iterative, 2ms
    public static int minPathSum1(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;

        int[][] minSums = new int[M][N];
        minSums[0][0] = grid[0][0];

        for (int x = 1; x < N; ++x) {
            minSums[0][x] = minSums[0][x - 1] + grid[0][x];
        }

        for (int y = 1; y < M; ++y) {
            minSums[y][0] = minSums[y - 1][0] + grid[y][0];
        }

        for (int y = 1; y < M; ++y) {
            for (int x = 1; x < N; ++x) {
                minSums[y][x] = Math.min(minSums[y - 1][x], minSums[y][x - 1]) + grid[y][x];
            }
        }

        return minSums[M - 1][N - 1];
    }

    // DP, iterative, condensed space, 1ms
    public static int minPathSum2(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;

        int[] minSums = new int[N];
        minSums[0] = grid[0][0];

        for (int x = 1; x < N; ++x) {
            minSums[x] = minSums[x - 1] + grid[0][x];
        }

        for (int y = 1; y < M; ++y) {
            for (int x = 0; x < N; ++x) {
                if (x == 0) {
                    minSums[x] = minSums[x] + grid[y][x];
                } else {
                    minSums[x] = Math.min(minSums[x - 1], minSums[x]) + grid[y][x];
                }
            }
        }

        return minSums[N - 1];
    }

    // DP, recursive, TLE
    public static int minPathSum3(int[][] grid) {
        return findMinPathSum(grid, grid.length - 1, grid[0].length - 1);
    }

    private static int findMinPathSum(int[][] grid, int y, int x) {
        if (y == 0 && x == 0) {
            return grid[0][0];
        }

        if (x == 0) {
            return findMinPathSum(grid, y - 1, x) + grid[y][x];
        }

        if (y == 0) {
            return findMinPathSum(grid, y, x - 1) + grid[y][x];
        }

        int minPathSumUp = findMinPathSum(grid, y - 1, x);
        int minPathSumLeft = findMinPathSum(grid, y, x - 1);

        return Math.min(minPathSumUp, minPathSumLeft) + grid[y][x];
    }

    // DP, recursive, memo, 1ms
    public static int minPathSum4(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;

        int[][] memo = new int[M][N];
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }

        return findMinPathSum(grid, M - 1, N - 1, memo);
    }

    private static int findMinPathSum(int[][] grid, int y, int x, int[][] memo) {

        if (y == 0 && x == 0) {
            if (memo[y][x] == -1) {
                memo[y][x] = grid[0][0];
            }
            return memo[y][x];
        }

        if (x == 0) {
            if (memo[y - 1][x] == -1) {
                memo[y - 1][x] = findMinPathSum(grid, y - 1, x, memo);
            }

            return memo[y - 1][x] + grid[y][x];
        }

        if (y == 0) {
            if (memo[y][x - 1] == -1) {
                memo[y][x - 1] = findMinPathSum(grid, y, x - 1, memo);
            }
            return memo[y][x - 1] + grid[y][x];
        }

        if (memo[y - 1][x] == -1) {
            memo[y - 1][x] = findMinPathSum(grid, y - 1, x, memo);
        }

        if (memo[y][x - 1] == -1) {
            memo[y][x - 1] = findMinPathSum(grid, y, x - 1, memo);
        }

        return Math.min(memo[y - 1][x], memo[y][x - 1]) + grid[y][x];
    }
}
