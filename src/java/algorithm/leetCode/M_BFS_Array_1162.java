package algorithm.leetCode;

/*
Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land,
find a water cell such that its distance to the nearest land cell is maximized, and return the distance.
If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance:
the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1

### Example
[[1,0,1],[0,0,0],[1,0,1]] -> 2
 [[1,0,0],[0,0,0],[0,0,0]] -> 4

*/

import java.util.*;

public class M_BFS_Array_1162 {

    // BFS, 17ms
    public int maxDistance(int[][] grid) {
        final int N = grid.length;
        final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        final int LAND = 1;
        final int WATER = 0;

        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                if (grid[y][x] == LAND) {
                    queue.offer(new int[]{y, x});
                    visited[y][x] = true;
                }
            }
        }

        int distance = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                int[] start = queue.poll();
                int y = start[0];
                int x = start[1];

                for (int[] direction : directions) {
                    int newY = y + direction[0];
                    int newX = x + direction[1];

                    if (newY < 0 || newY >= N || newX < 0 || newX >= N) {
                        continue;
                    }

                    if (grid[newY][newX] == WATER && !visited[newY][newX]) {
                        queue.offer(new int[]{newY, newX});
                        visited[newY][newX] = true;
                    }
                }
            }
            distance++;
        }

        return distance <= 0 ? -1 : distance;
    }

}