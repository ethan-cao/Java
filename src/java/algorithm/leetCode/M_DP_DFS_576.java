package algorithm.leetCode;

/*
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball,
you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right).
However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary.
The answer may be very large, return it after mod 10^9 + 7.

Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].

### Example
Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6

Input: m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12

 */

public class M_DP_DFS_576 {

    public static void main(String... args) {
        System.out.println(findPaths(2, 2, 2, 0, 0));  // 6
        System.out.println(findPaths(1, 3, 3, 0, 1));  // 12
    }

    // DFS, ms
    private static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static int MOD = 1000000007;

    public static int findPaths(int m, int n, int N, int i, int j) {
        Long[][][] cache = new Long[m][n][N + 1];
        long pathCount = visit(m, n, N, i, j, cache) % MOD;

        return (int) pathCount;
    }

    private static Long visit(int m, int n, int N, int i, int j, Long[][][] cache) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return Long.valueOf(1);
        }

        if (N == 0) {
            return Long.valueOf(0);
        }

        if (cache[i][j][N] != null) {
            return cache[i][j][N];
        }

        cache[i][j][N] = Long.valueOf(0);

        for (int direction[] : directions) {
            int newI = i + direction[0];
            int newJ = j + direction[1];

            long pathCount = visit(m, n, N - 1, newI, newJ, cache) % MOD;

            cache[i][j][N] = (cache[i][j][N] + pathCount) % MOD;
        }

        return cache[i][j][N];
    }

    // DFS, ms
    public static int findPaths1(int m, int n, int N, int i, int j) {
        int pathCount = 0;

        return pathCount;
    }
}