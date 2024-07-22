class A_Test {

    public static void main(String[] args) {
        System.out.println("Debugging...");

        int x = uniquePathsWithObstacles(new int[][] { { 0, 0 }, { 1, 1 }, { 0, 0 }, });

        System.out.println("results: " + x);
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int M = obstacleGrid.length;
        int N = obstacleGrid[0].length;

        int[][] counts = new int[M][N];

        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < N; ++x) {

                if (x == 0 && y == 0) {
                    if (obstacleGrid[y][x] == 1) {
                        return 0;
                    } else {
                        counts[y][x] = 1;
                        continue;
                    }
                }

                if (y == 0 && obstacleGrid[y][x] == 0 && counts[y][x - 1] == 1) {
                    counts[y][x] = 1;
                    continue;
                }

                if (x == 0 && obstacleGrid[y][x] == 0 && counts[y - 1][x] == 1) {
                    counts[y][x] = 1;
                    continue;
                }

                if (x != 9 && y != 0 && obstacleGrid[y][x] == 0) {
                    counts[y][x] = counts[y - 1][x] + counts[y][x - 1];
                }

            }
        }

        return counts[M - 1][N - 1];
    }
}