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

        for (int x = 0; x < SIZE; ++x) {
            minSums[SIZE - 1][x] = triangle.get(SIZE - 1).get(x);
        }

        for (int y = SIZE - 2; y >= 0; --y) {
            for (int x = 0; x <= y; ++x) {
                minSums[y][x] = Math.min(minSums[y + 1][x], minSums[y + 1][x + 1]) + triangle.get(y).get(x);
            }
        }

        return minSums[0][0];
    }

    // DP, Iterative, condensed space, 2ms
    public int minimumTotal(List<List<Integer>> triangle) {
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

}

