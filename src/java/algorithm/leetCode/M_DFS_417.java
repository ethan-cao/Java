package algorithm.leetCode;

/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

The order of returned grid coordinates does not matter.
Both m and n are less than 150.

### Example
  Pacific  ~   ~   ~   ~   ~
        ~  1   2   2   3  (5) *
        ~  3   2   3  (4) (4) *
        ~  2   4  (5)  3   1  *
        ~ (6) (7)  1   4   5  *
        ~ (5)  1   1   2   4  *
           *   *   *   *   *  Atlantic
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

        for (int y = 0; y < M; ++y) {
            flow(matrix, y, 0, Integer.MIN_VALUE, canFlowToPacific);       // first column
            flow(matrix, y, N - 1, Integer.MIN_VALUE, canFlowToAtlantic);  // last column
        }

        for (int x = 0; x < N; ++x) {
            flow(matrix, 0, x, Integer.MIN_VALUE, canFlowToPacific);       // first row
            flow(matrix, M - 1, x, Integer.MIN_VALUE, canFlowToAtlantic);  // last row
        }

        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < N; ++x) {
                if (canFlowToPacific[y][x] && canFlowToAtlantic[y][x]) {
                    result.add(Arrays.asList(y, x));
                }
            }
        }

        return result;
    }

    private static final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static void flow(int[][] matrix, int y, int x, int previousHeight, boolean[][] canFlow) {
        if (y < 0 || y >= matrix.length || x < 0 || x >= matrix[0].length) {
            return;
        }

        int height = matrix[y][x];

        if (canFlow[y][x] || height < previousHeight) {
            return;
        }

        canFlow[y][x] = true;

        for (int[] direction : directions) {
            flow(matrix, y + direction[0], x + direction[1], height, canFlow);
        }
    }

    // BFS, 10ms
    public List<List<Integer>> pacificAtlantic2(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();

        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        final int M = matrix.length;
        final int N = matrix[0].length;

        boolean[][] canFlowToPacific = new boolean[M][N];
        Deque<int[]> pacificCells = new ArrayDeque<>();

        boolean[][] canFlowToAtlantic = new boolean[M][N];
        Deque<int[]> AtlanticCells = new ArrayDeque<>();

        for (int y = 0; y < M; ++y) {
            canFlowToPacific[y][0] = true;
            pacificCells.offer(new int[]{y, 0});

            canFlowToAtlantic[y][N - 1] = true;
            AtlanticCells.offer(new int[]{y, N - 1});
        }

        for (int x = 1; x < N; ++x) {
            canFlowToPacific[0][x] = true;
            pacificCells.offer(new int[]{0, x});

            canFlowToAtlantic[M - 1][x - 1] = true;
            AtlanticCells.offer(new int[]{M - 1, x - 1});
        }

        while (pacificCells.size() > 0) {
            int[] cell = pacificCells.poll();

            for (int[] neighbour : getNeighbors(cell, matrix, M, N)) {
                int y = neighbour[0];
                int x = neighbour[1];

                if (canFlowToPacific[y][x]) {
                    continue;
                }

                canFlowToPacific[y][x] = true;
                pacificCells.offer(new int[]{y, x});
            }
        }

        while (AtlanticCells.size() > 0) {
            int[] cell = AtlanticCells.poll();

            for (int[] neighbour : getNeighbors(cell, matrix, M, N)) {
                if (canFlowToAtlantic[neighbour[0]][neighbour[1]]) {
                    continue;
                }

                canFlowToAtlantic[neighbour[0]][neighbour[1]] = true;
                AtlanticCells.offer(new int[]{neighbour[0], neighbour[1]});
            }
        }

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (canFlowToPacific[y][x] && canFlowToAtlantic[y][x]) {
                    result.add(Arrays.asList(y, x));
                }
            }
        }

        return result;
    }

    public List<int[]> getNeighbors(int[] cell, int[][] matrix, int M, int N) {
        int y = cell[0];
        int x = cell[1];
        int height = matrix[y][x];

        List<int[]> neighbors = new ArrayList<>();

        if (x - 1 >= 0 && matrix[y][x - 1] >= height) {
            neighbors.add(new int[]{y, x - 1});
        }
        if (x + 1 < N && matrix[y][x + 1] >= height) {
            neighbors.add(new int[]{y, x + 1});
        }
        if (y - 1 >= 0 && matrix[y - 1][x] >= height) {
            neighbors.add(new int[]{y - 1, x});
        }
        if (y + 1 < M && matrix[y + 1][x] >= height) {
            neighbors.add(new int[]{y + 1, x});
        }

        return neighbors;
    }

}