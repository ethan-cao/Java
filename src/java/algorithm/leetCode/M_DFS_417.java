package algorithm.leetCode;

/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

The order of returned grid coordinates does not matter.
Both m and n are less than 150.

### Example
Given the following 5x5 matrix:
  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic
Return: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

*/

import java.util.*;

public class M_DFS_417 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        System.out.println(pacificAtlantic(matrix));
    }

    // DFS, 4ms
    public static List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        final int M = matrix.length;
        final int N = matrix[0].length;

        boolean[][] canFlowToPacific = new boolean[M][N];
        boolean[][] canFlowToAtlantic = new boolean[M][N];

        for (int i = 0; i < N; ++i) {
            flow(matrix, 0, i, Integer.MIN_VALUE, canFlowToPacific);
            flow(matrix, M - 1, i, Integer.MIN_VALUE, canFlowToAtlantic);
        }

        for (int i = 0; i < M; ++i) {
            flow(matrix, i, 0, Integer.MIN_VALUE, canFlowToPacific);
            flow(matrix, i, N - 1, Integer.MIN_VALUE, canFlowToAtlantic);
        }

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (canFlowToPacific[i][j] && canFlowToAtlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    static int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void flow(int[][] matrix, int x, int y, int height, boolean[][] canFlow) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || canFlow[x][y] || matrix[x][y] < height) {
            return;
        }

        canFlow[x][y] = true;

        for (int[] direction : directions) {
            flow(matrix, x + direction[0], y + direction[1], matrix[x][y], canFlow);
        }
    }

}