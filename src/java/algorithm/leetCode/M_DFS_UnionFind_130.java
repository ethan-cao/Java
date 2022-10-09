package algorithm.leetCode;

/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

### Example
X X X X
X O O X
X X O X
X O X X
->
X X X X
X X X X
X X X X
X O X X

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
Two cells are connected if they are adjacent cells connected horizontally or vertically.

*/

import java.util.Arrays;

public class M_DFS_UnionFind_130 {

    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };

        solve(board);

        System.out.println(Arrays.deepToString(board));
    }

    // DFS, 1ms
    public static void solve(char[][] board) {
        final int M = board.length;
        final int N = M == 0 ? 0 : board[0].length;

        // avoid stack overflow
        if (M == 0 || M < 3 || N == 0 || N < 3) {
            return;
        }

        // check the four border of the matrix.
        // If there is a element is 'O', update it and all its connected neighbor 'O' to '1'.
        for (int i = 0; i < M; ++i) {
            if (board[i][0] == 'O') {
                visit(board, i, 0);
            }

            if (board[i][N - 1] == 'O') {
                visit(board, i, N - 1);
            }
        }

        // optimization: [1, N-1]
        for (int j = 1; j < N - 1; ++j) {
            if (board[0][j] == 'O') {
                visit(board, 0, j);
            }

            if (board[M - 1][j] == 'O') {
                visit(board, M - 1, j);
            }
        }

        // update all the 'O' to 'X', and restore '1' to 'O';
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }

                if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void visit(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }

        if (board[x][y] == 'X' || board[x][y] == '1') {
            return;
        }

        if (board[x][y] == 'O') {
            board[x][y] = '1';
        }

        visit(board, x + 1, y);
        visit(board, x - 1, y);
        visit(board, x, y + 1);
        visit(board, x, y - 1);
    }

    // UnionFind
    public static void solve0(char[][] board) {

    }
}