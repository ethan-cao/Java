package algorithm.leetCode;

/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
m and n will be at most 100.
[start]  *  *  *  *  *  *
      *  *  *  *  *  *  *
      *  *  *  *  *  *  [Finish]
How many possible unique paths are there?

### Example
m = 3, n = 2 -> 3
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
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

    // DP iterative, 0ms
    // Time: O(M*N), Space: O(M*N)
    // analyse with tabulation
    public static int uniquePaths2(int m, int n) {
        int[][] result = new int[m][n];

        for (int x = 0; x < n; ++x) {
            result[0][x] = 1;
        }

        for (int y = 0; y < m; ++y) {
            result[y][0] = 1;
        }

        for (int y = 1; y < m; ++y) {
            for (int x = 1; x < n; ++x) {
                result[y][x] = result[y - 1][x] + result[y][x - 1];
            }
        }

        return result[m - 1][n - 1];
    }

    // DP, iterative, space, 0ms
    // Time: O(M*N), Space: O(N)
    public static int uniquePaths3(int m, int n) {
        int[] result = new int[n];

        Arrays.fill(result, 1);

        for (int y = 1; y < m; ++y) {
            for (int x = 1; x < n; ++x) {
                result[x] = result[x] + result[x - 1];
            }
        }

        return result[n - 1];
    }

    // DP, recursive, LTE
    // Time
    // use tree to analyse all possible cases, then came up with recursion solution
    public static int uniquePaths(int m, int n) {
        return countPath(m - 1, n - 1);
    }

    private static int countPath(int y, int x) {
        if (y == 0 || x == 0) {
            return 1;
        }

        return countPath(y - 1, x) + countPath(y, x - 1);
    }

    // DP, recursive, memoization, 0ms
    public static int uniquePaths1(int m, int n) {
        int[][] memo = new int[m][n];
        return countPath(m - 1, n - 1, memo);
    }

    private static int countPath(int y, int x, int[][] memo) {
        if (y == 0 || x == 0) {
            return 1;
        }

        if (memo[y - 1][x] == 0) {
            memo[y - 1][x] = countPath(y - 1, x, memo);
        }

        if (memo[y][x - 1] == 0) {
            memo[y][x - 1] = countPath(y, x - 1, memo);
        }

        return memo[y - 1][x] + memo[y][x - 1];
    }


    // Math
    // Time: O(N), Space: O(N)
    // move from (1,1) to (m, n), there are (m-1)+(n-1) steps needed, namely m+n-2
    // in m+n-2 steps, if we identify m-1 steps towards right, then n-1 steps towards down is definite
    // then we know how many ways to reach from (1,1) to (m,m)
    // Thus we just need to get P(m+n-2, m-1)
    public static int uniquePaths4(int m, int n) {
        double uniquePath = 1;   // pay attention to precision !!!

        // m/1 * (m+1)/2 * (m+2)/3 ... * (m-1+n-1)/(n-1)
        for (double i = 1; i < n; ++i) {
            uniquePath *= (m - 1 + i) / i;
        }

        return (int) Math.round(uniquePath);
    }
}
