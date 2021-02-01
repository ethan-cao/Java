public class Test {

    public static void main(String[] args) {
        int size1 = 4;
        int[][] pyramid1 = {
                {1},
                {2, 3},
                {4, 5, 6},
                {7, 8, 9, 10},
        };
        assert getShortestPathSum2(size1, pyramid1) == 14;

        int size2 = 4;
        int[][] pyramid2 = {
                {3},
                {7, 4},
                {2, 4, 6},
                {8, 5, 9, 3},
        };
        assert getShortestPathSum2(size2, pyramid2) == 16;

        int size3 = 15;
        int[][] pyramid3 = {
                {75},
                {95, 64},
                {17, 47, 82},
                {18, 35, 87, 10},
                {20, 4, 82, 47, 65},
                {19, 1, 23, 75, 3, 34},
                {88, 2, 77, 73, 7, 63, 67},
                {99, 65, 4, 28, 6, 16, 70, 92},
                {41, 41, 26, 56, 83, 40, 80, 70, 33},
                {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
                {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
                {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
                {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
                {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
                {4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23},
        };
        assert getShortestPathSum2(size3, pyramid3) == 447;
    }

    // Dynamic programming, iterative
    // Time: O(N^2), Space: O(N^2)
    public static int getShortestPathSum1(int size, int[][] pyramid) {
        if (size <= 0 || pyramid == null || pyramid.length == 0 || pyramid[0] == null || pyramid[0].length == 0) {
            return 0;
        }

        // shortestPathSums[y][x]: the shortest path sum from the bottom to (y, x)
        int[][] shortestPathSums = new int[size][size];
        for (int x = 0; x < size; ++x) {
            shortestPathSums[size - 1][x] = pyramid[size - 1][x];
        }

        for (int y = size - 2; y >= 0; --y) {
            for (int x = 0; x <= y; ++x) {
                shortestPathSums[y][x] = Math.min(shortestPathSums[y + 1][x], shortestPathSums[y + 1][x + 1]) + pyramid[y][x];
            }
        }

        return shortestPathSums[0][0];
    }

    // Dynamic programming, iterative, condensed space
    // Time: O(N^2), Space: O(N)
    public static int getShortestPathSum2(int size, int[][] pyramid) {
        if (size <= 0 || pyramid == null || pyramid.length == 0 || pyramid[0] == null || pyramid[0].length == 0) {
            return 0;
        }

        int[] shortestPathSums = new int[size];
        for (int x = 0; x < size; ++x) {
            shortestPathSums[x] = pyramid[size - 1][x];
        }

        for (int y = size - 2; y >= 0; --y) {
            for (int x = 0; x <= y; ++x) {
                shortestPathSums[x] = Math.min(shortestPathSums[x], shortestPathSums[x + 1]) + pyramid[y][x];
            }
        }

        return shortestPathSums[0];
    }
}