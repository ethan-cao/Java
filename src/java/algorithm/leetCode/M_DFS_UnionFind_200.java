package algorithm.leetCode;

/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

### Example
11110
11010
11000
00000 -> 1

11000
11000
00100
00011 -> 3

*/

public class M_DFS_UnionFind_200 {

    public static void main(String[] args) {
        char[][] grid1 = {
                {'1', '1', '1', '0'},
                {'1', '1', '1', '0'},
                {'1', '0', '0', '0'},
                {'1', '1', '0', '0'},
                {'0', '0', '0', '0'}
        };
        System.out.println(numIslands(grid1)); // 1

        char[][] grid2 = {
                {'1', '1', '0', '0'},
                {'1', '1', '0', '0'},
                {'0', '0', '1', '0'},
                {'0', '0', '0', '1'},
                {'0', '0', '0', '1'}
        };
        System.out.println(numIslands(grid2)); // 3

        char[][] grid4 = {
                {'1', '0', '1', '1', '1'},
                {'1', '0', '1', '0', '1'},
                {'1', '1', '1', '0', '1'}
        };
        System.out.println(numIslands(grid4)); // 1
    }

    static private char LAND = '1';
    static private char WATER = '0';
    static private int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // DFS ~1ms
    // Time:  O(MN), M = grid.length, N = grid[0].length
    public static int numIslands2(char[][] grid) {
        int islandCount = 0;

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {

                if (grid[i][j] == WATER) {
                    continue;
                }

                checkSurrounding(grid, i, j);
                islandCount++;
            }
        }

        return islandCount;
    }

    private static void checkSurrounding(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }

        if (grid[i][j] == WATER) {
            return;
        }

        grid[i][j] = WATER;

        for (int[] direction : DIRECTIONS) {
            checkSurrounding(grid, i + direction[0], j + direction[1]);
        }
    }

    // BFS
    public static int numIslands1(char[][] grid) {
        return 1;
    }

    // Union Find
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int columnSize = grid.length;
        int rowSize = grid[0].length;
        UnionFind uf = new UnionFind(grid);

        for (int i = 0; i < columnSize; ++i) {
            for (int j = 0; j < rowSize; ++j) {

                if (grid[i][j] == '0') {
                    continue;
                }

                int cell = getID(i, j, rowSize);

                // examine from top to bottom, vertically
                int columnID = i + 1;
                while (columnID < columnSize) {
                    if (grid[columnID][j] == '0') {
                        break; // when there is water, land is not connected
                    }

                    uf.union(cell, getID(columnID, j, rowSize));
                    columnID++;
                }

                // examine from left to right, horizontally
                int rowID = j + 1;
                while (rowID < rowSize) {
                    if (grid[i][rowID] == '0') {
                        break; // when there is water, land is not connected
                    }
                    uf.union(cell, getID(i, rowID, rowSize));
                    rowID++;
                }
            }
        }

        return uf.count;
    }

    // !!! it's easy to make mistake when calculate id, so it's better to use a method to encapsulate the calculation
    private static int getID(int x, int y, int rowSize) {
        return rowSize * x + y;
    }

    // Path compression, weighted, UnionFind
    static class UnionFind {
        int[] data;
        int[] size;
        int count = 0;

        public UnionFind(char[][] grid) {
            // !!! transform 2-d to 1-d to use union find
            this.data = new int[grid.length * grid[0].length];
            this.size = new int[data.length];

            for (int i = 0; i < grid.length * grid[0].length; i++) {
                this.data[i] = i;
                this.size[i] = 1;
            }

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                    }
                }
            }
        }

        public void union(int a, int b) {
            int rootA = getRoot(a);
            int rootB = getRoot(b);

            if (rootA == rootB) {
                return;
            }

            if (size[rootA] < size[rootB]) {
                data[rootA] = rootB;
                size[rootB] += size[rootA];
            } else {
                data[rootB] = rootA;
                size[rootA] += size[rootB];
            }

            count--;
        }

        private int getRoot(int i) {
            int root = data[i];

            while (root != data[root]) {
                data[i] = data[root];
                root = data[i];
            }

            return root;
        }
    }

}