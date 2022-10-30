package algorithm.leetCode;

/*
Given a triangle array, return the minimum path sum from top to bottom.
For each step, you may move to an adjacent number of the row below.
More formally, if you are on index i on the current row,
you may move to either index i or index i + 1 on the next row.

### Example
[[2],[3,4],[6,5,7],[4,1,8,3]] -> 11
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11
*/

import java.util.List;

public class M_DP_120 {

    // DP, Iterative, 2ms
    public int minimumTotal0(List<List<Integer>> triangle) {
        final int SIZE = triangle.size();
        int[][] minSums = new int[SIZE][SIZE];

         // BASE, the last row
        for (int x = 0; x < SIZE; ++x) {
            minSums[SIZE - 1][x] = triangle.get(SIZE - 1).get(x);
        }

        for (int y = SIZE - 2; y >= 0; --y) {
            for (int x = 0; x <= y; ++x) {
                minSums[y][x] = triangle.get(y).get(x) + Math.min(minSums[y + 1][x], minSums[y + 1][x + 1]);
            }
        }

        return minSums[0][0];
    }

    // DP, Iterative, condensed space, 2ms
    public int minimumTotal1(List<List<Integer>> triangle) {
        final int SIZE = triangle.size();
        int[] minSums = new int[SIZE];

        for (int x = 0; x < SIZE; ++x) {
            minSums[x] = triangle.get(SIZE - 1).get(x);
        }

        for (int y = SIZE - 2; y >= 0; --y) {
            for (int x = 0; x <= y; ++x) {
                minSums[x] = Math.min(minSums[x], minSums[x + 1]) + triangle.get(y).get(x);
            }
        }

        return minSums[0];
    }

    // DP, recursive, TLE
    public int minimumTotal2(List<List<Integer>> triangle) {
        return getPathSum(triangle, 0, 0);
    }

    private int getPathSum(List<List<Integer>> triangle, int y, int x) {
        if (y >= triangle.size()) {
            return 0;
        }

        int leftPathSum = getPathSum(triangle, y + 1, x);
        int rightPathSum = getPathSum(triangle, y + 1, x + 1);

        return triangle.get(y).get(x) + Math.min(leftPathSum, rightPathSum);
    }

    // DP, recursive, memo, 0ms
    public int minimumTotal3(List<List<Integer>> triangle) {
        final int SIZE = triangle.size();
        int[][] memo = new int[SIZE][SIZE];
        return getPathSum(triangle, 0, 0, memo);
    }

    private int getPathSum(List<List<Integer>> triangle, int y, int x, int[][] memo) {
        if (y >= triangle.size()) {
            return 0;
        }

        if (memo[y][x] != 0) {
            return memo[y][x];
        }

        int leftPathSum = getPathSum(triangle, y + 1, x, memo);
        int rightPathSum = getPathSum(triangle, y + 1, x + 1, memo);

        memo[y][x] = Math.min(leftPathSum, rightPathSum) + triangle.get(y).get(x);

        return memo[y][x];
    }
}

