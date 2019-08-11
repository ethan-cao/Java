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

*/

public class M_DP_62 {

    public static void main(String... args) {
        System.out.println(uniquePaths1(1, 1)); // 1
        System.out.println(uniquePaths1(2, 2)); // 2
        System.out.println(uniquePaths1(3, 2)); // 3
        System.out.println(uniquePaths1(3, 3)); // 6
        System.out.println(uniquePaths1(7, 3)); // 28
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

        if (i < m) {
            path += getPathNumber(m, n, i + 1, j);
        }

        if (j < n) {
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

    // iterative
    public static int uniquePaths2(int m, int n) {
        return 1;
    }
}
