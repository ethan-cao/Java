package algorithm.leetCode;

/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero),
return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point
until it hits the wall since the wall is too strong to be destroyed.

Note: You can only put the bomb at an empty cell.

### Example
Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3
Explanation: For the given grid,
0 E 0 0
E 0 W E
0 E 0 0
Placing a bomb at (1,1) kills 3 enemies.

*/

public class M_DP_361 {

    public static void main(String[] args) {
        System.out.println(maxKilledEnemies(new char[][]{
                {'0', 'E', '0', '0'},
                {'E', '0', 'W', 'E'},
                {'0', 'E', '0', '0'},
        })); // 3
    }

    // Time O(MN), Space: O(n)
    public static int maxKilledEnemies(char[][] grid) {
        char WALL = 'W';
        char ENEMY = 'E';
        char EMPTY = '0';
        int M = grid.length;
        int N = M > 0 ? grid[0].length : 0;

        int maxKill = 0;

        int enemyCountPerCurrentRow = 0;
        int[] enemyCountPerCols = new int[N];

        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {

                // cache enemy count in the current row between two walls
                if (col == 0 || grid[row][col - 1] == WALL) {
                    enemyCountPerCurrentRow = 0;

                    for (int i = col; i < N && grid[row][i] != WALL; i++) {
                        if (grid[row][i] == ENEMY) {
                            enemyCountPerCurrentRow++;
                        }
                    }
                }

                // cache enemy count in the current col between two walls
                if (row == 0 || grid[row - 1][col] == WALL) {
                    enemyCountPerCols[col] = 0;

                    for (int i = row; i < M && grid[i][col] != WALL; i++) {
                        if (grid[i][col] == ENEMY) {
                            enemyCountPerCols[col]++;
                        }
                    }
                }

                // if this is a position to place the bomb, get the current maxKill
                if (grid[row][col] == EMPTY) {
                    maxKill = Math.max(maxKill, enemyCountPerCurrentRow + enemyCountPerCols[col]);
                }
            }
        }

        return maxKill;
    }
}

