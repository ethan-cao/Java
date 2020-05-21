package algorithm.leetCode;

/*
In a given 2D binary array A, there are two islands.
(An island is a 4-directionally connected group of 1s not connected to any other 1s.)
Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

1 <= A.length = A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1

### Example
[[0,1],[1,0]] -> 1
[[0,1,0],[0,0,0],[0,0,1]] -> 2
[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]] -> 1

*/

import java.util.*;

public class M_BFS_Array_934 {

    public static void main(String... args) {
        System.out.println(shortestBridge(new int[][]{{0, 1}, {1, 0}})); // 1
    }

    // BFS
    // Time O(), Space: O()
    public static int shortestBridge(int[][] A) {
        int flipCount = 0;
        int M = A.length;
        int N = M == 0 ? 0 : A[0].length;
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Deque<int[]> queue = new ArrayDeque<>();
        boolean hasIsland = false;
        boolean[][] isIsland = new boolean[M][N];

        // dfs to find one island, mark it in isIsland[][]
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (A[i][j] == 0) {
                    continue;
                }

                visit(A, i, j, isIsland, queue, directions);
                hasIsland = true;
                break;
            }

            if (hasIsland) {
                break;
            }
        }

        // bfs to expand this island
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                int[] land = queue.poll();

                for (int[] direction : directions) {
                    int nextX = land[0] + direction[0];
                    int nextY = land[1] + direction[1];

                    if (nextX < 0 || nextX >= A.length || nextY < 0 || nextY >= A[0].length || isIsland[nextX][nextY]) {
                        continue;
                    }

                    // when two islands connects, we have the flipCount
                    if (A[nextX][nextY] == 1) {
                        return flipCount;
                    }

                    isIsland[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY});
                }
            }

            flipCount++;
        }

        return -1;
    }

    // mark island in isIsland[][]
    private static void visit(int[][] A, int x, int y, boolean[][] isIsland, Deque<int[]> queue, int[][] directions) {
        if (x < 0 || x >= A.length || y < 0 || y >= A[0].length || A[x][y] == 0 || isIsland[x][y]) {
            return;
        }

        isIsland[x][y] = true;
        queue.offer(new int[]{x, y});

        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];

            visit(A, nextX, nextY, isIsland, queue, directions);
        }
    }


}