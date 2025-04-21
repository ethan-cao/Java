package algorithm.leetCode;

/*
On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves.
The rows and columns are 0-indexed, so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).
A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.
Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
The knight continues moving until it has made exactly k moves or has moved off the chessboard.
Return the probability that the knight remains on the board after it has stopped moving.

1 <= n <= 25
0 <= k <= 100
0 <= row, column <= n

### Example
Input: n = 3, k = 2, row = 0, column = 0
Output: 0.06250
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.

Input: n = 1, k = 0, row = 0, column = 0
Output: 1.00000

*/

public class M_DP_688 {

    public static void main(String[] args) {
        System.out.println(knightProbability1(3, 2, 0, 0)); // 0.06250
        System.out.println(knightProbability1(8, 20, 6, 4)); // 0.06250
    }

    private static int[][] directions = new int[][] { 
        { -2, -1 }, { -1, -2 }, 
        { 1, -2 }, { 2, -1 }, 
        { 2, 1 }, { 1, 2 },
        { -1, 2 }, { -2, 1 } 
    };

    // DP, iterative
    public double knightProbability11(int n, int k, int row, int column) {
        // probabilities[i][y][x]: probability when move i step arriving at (y, x)
        double[][][] probabilities = new double[k + 1][n][n];

        // 100% on the starting location without move
        probabilities[0][row][column] = 1.0;
        
        // find probabilities of arriving at each cell within the board
        for (int step = 1; step <= k; ++step) {
            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < n; ++x) {

                    for (int[] direction : directions) {
                        int preY = y - direction[0];
                        int preX = x - direction[1];

                        // if the previous position is within board,
                        // 1/8 of the probability arriving at the previous location can sum to the probability arriving at the next position
                        if (preY >= 0 && preY < n && preX >= 0 && preX < n) {
                            //!!! must use 8.0 or 8d, otherwise 0/8 is 0
                            probabilities[step][y][x] += (probabilities[step - 1][preY][preX] * (1 / 8.0));
                        }
                    }
                }
            }
        }

        double result = 0;

        // sum all the probabilities up, to get the probability of staying in the board after k moves
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                result += probabilities[k][y][x];
            }
        }

        return result;
    }

    // DP
    // 7ms
    public static double knightProbability1(int n, int k, int row, int column) {
        double[][][] memo = new double[n][n][k + 1];

        return move(n, k, row, column, memo);
    }

    public static double move(int n, int k, int row, int col, double[][][] memo) {
        if (row < 0 || row > n - 1 || col < 0 || col > n - 1) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }

        if (memo[row][col][k] != 0) {
            return memo[row][col][k];
        }

        double probability = 0;

        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            probability += 0.125 * move(n, k - 1, nextRow, nextCol, memo);
        }

        memo[row][col][k] = probability;

        return memo[row][col][k];
    }


    // BFS, TLE
    public static double knightProbability(int n, int k, int row, int column) {
        return move(n, k, row, column);
    }

    public static double move(int n, int k, int row, int col) {
        if (row < 0 || row > n - 1 || col < 0 || col > n - 1) {
            return 0;
        }

        if (k == 0) {
            return 1;
        }

        // 0.125 = 1 / 8, 1 of 8 possible move
        double curProbability = 0.125;

        double probability = 0;

        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            probability += curProbability * move(n, k - 1, nextRow, nextCol);
        }

        return probability;
    }
}
