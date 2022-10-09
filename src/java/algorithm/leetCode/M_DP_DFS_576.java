package algorithm.leetCode;

/*
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball,
you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right).
However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary.
The answer may be very large, return it after mod 10^9 + 7.

Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50]. N is in range [0,50].

### Example
m = 2, n = 2, N = 2, i = 0, j = 0 -> 6
m = 1, n = 3, N = 3, i = 0, j = 1 -> 12

*/

public class M_DP_DFS_576 {

    public static void main(String... args) {
        System.out.println(findPaths1(2, 2, 2, 0, 0));  // 6
        System.out.println(findPaths1(1, 3, 3, 0, 1));  // 12
    }

    private static int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static int MOD = 1000000007;

    // DFS, brute force
    // Time:  O(Nmn), 20ms
    public static int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
        Long[][][] cache = new Long[m][n][maxMove + 1];

        long pathCount = find(m, n, maxMove, startRow, startColumn, cache) % MOD;

        return (int) pathCount;
    }

    private static Long find(int m, int n, int maxMove, int y, int x, Long[][][] cache) {
        if (y < 0 || y >= m || x < 0 || x >= n) {
            return 1L;
        }

        if (maxMove == 0) {
            return 0L;
        }

        if (cache[y][x][maxMove] != null) {
            return cache[y][x][maxMove];
        }

        cache[y][x][maxMove] = 0L;

        for (int[] direction : DIRECTIONS) {
            int nextY = y + direction[0];
            int nextX = x + direction[1];

            cache[y][x][maxMove] += find(m, n, maxMove - 1, nextY, nextX, cache);
            cache[y][x][maxMove] %= MOD;
        }

        return cache[y][x][maxMove];
    }

    // DP, iterative, 3d
    // 19ms
    public static int findPaths2(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] counts = new int[maxMove + 1][m][n];

        for (int move = 1; move <= maxMove; ++move) {

            for (int y = 0; y < m; ++y) {
                for (int x = 0; x < n; ++x) {

                    for (int[] direction : DIRECTIONS) {
                        int nextY = y + direction[0];
                        int nextX = x + direction[1];

                        if (nextY < 0 || nextY >= m || nextX < 0 || nextX >= n) {
                            counts[move][y][x] += 1;
                        } else {
                            // derived from recursive approach
                            counts[move][y][x] += counts[move - 1][nextY][nextX];

                            //  counts[move][y][x] only depends on counts[move - 1][nextY][nextX], the previous row
                        }

                        counts[move][y][x] %= MOD;
                    }
                }
            }
        }

        return counts[maxMove][startRow][startColumn];
    }

    // DP, iterative, 2d
    // 27ms
    public static int findPaths3(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][] counts = new int[m][n];

        for (int move = 1; move <= maxMove; move++) {
            int[][] next = new int[m][n];

            for (int y = 0; y < m; ++y) {
                for (int x = 0; x < n; ++x) {

                    for (int[] direction : DIRECTIONS) {
                        int nextY = y + direction[0];
                        int nextX = x + direction[1];

                        if (nextY < 0 || nextY >= m || nextX < 0 || nextX >= n) {
                            next[y][x] += 1;
                        } else {
                            next[y][x] += counts[nextY][nextX];
                        }

                        next[y][x] %= MOD;
                    }
                }
            }

            counts = next;
        }

        return counts[startRow][startColumn];
    }
}