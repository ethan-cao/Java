package algorithm.leetCode;

/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 m and n will be at most 100.

[start]  *  *  *  *  *  *
      *  *  *  *  *  *  *
      *  *  *  *  *  *  [Finish]

(Above is a 7 x 3 grid)

How many possible unique paths are there?

### Example
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right


Input: m = 7, n = 3
Output: 28

Related :  64, 63
*/

import java.util.Arrays;

public class M_DP_Math_62 {

    public static void main(String... args) {
        System.out.println(uniquePaths4(1, 1)); // 1
        System.out.println(uniquePaths4(2, 2)); // 2
        System.out.println(uniquePaths4(3, 2)); // 3
        System.out.println(uniquePaths4(3, 3)); // 6
        System.out.println(uniquePaths4(3, 7)); // 28
        System.out.println(uniquePaths4(7, 3)); // 28
        System.out.println(uniquePaths4(10, 10)); // 48620
    }

    // use tree to analyse all possible cases, then came up with recursion solution
    public static int uniquePaths(int m, int n) {
        return getPathNumber(m, n, 0, 0);
    }

    private static int getPathNumber(int m, int n, int i, int j) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        int path = 0;

        if (i + 1 < m) {
            path += getPathNumber(m, n, i + 1, j);
        }

        if (j + 1 < n) {
            path += getPathNumber(m, n, i, j + 1);
        }

        return path;
    }

    // uniquePaths, optimized with cache
    public static int uniquePaths1(int m, int n) {
        return getPathNumber(m, n, 0, 0, new int[m][n]);
    }

    private static int getPathNumber(int m, int n, int i, int j, int[][] cache) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        int path = 0;

        if (i + 1 < m) {
            if (cache[i + 1][j] == 0) {
                cache[i + 1][j] = getPathNumber(m, n, i + 1, j, cache);
            }

            path += cache[i + 1][j];
        }

        if (j < n) {
            if (cache[i][j + 1] == 0) {
                cache[i][j + 1] = getPathNumber(m, n, i, j + 1, cache);
            }

            path += cache[i][j + 1];
        }

        return path;
    }

    // iterative, analyse with tabulation
    public static int uniquePaths2(int m, int n) {
        int[][] grid = new int[m][n];

        Arrays.fill(grid[0], 1);
        for (int i = 1; i < m; ++i) {
            grid[i][0] = 1;
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }

        return grid[m - 1][n - 1];
    }

    // uniquePaths2 with optimization
    public static int uniquePaths3(int m, int n) {
        int[] grid = new int[n];

        Arrays.fill(grid, 1);

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                grid[j] = grid[j] + grid[j - 1];
            }
        }

        return grid[n - 1];
    }

    // move from (1,1) to (m, n), there are (m-1)+(n-1) steps needed, namely m+n-2
    // in m+n-2 steps, if we identify m-1 steps towards right, then n-1 steps towards down is definite
    // then we know how many ways to reach from (1,1) to (m,m)
    // Thus we just need to get P(m+n-2, m-1)
    public static int uniquePaths4(int m, int n) {
        double uniquePath = 1;   // pay attention to precision !!!

        // m/1 * (m+1)/2 * (m+2)/3 ... * (m-1+n-1)/(n-1)
        for (double i = 1; i < n; ++i) {
            uniquePath *= (m - 1 + i) / i;
//            System.out.println(uniquePath);
        }

        return (int) Math.round(uniquePath);
    }
}
