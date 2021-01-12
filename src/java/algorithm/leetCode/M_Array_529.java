package algorithm.leetCode;

/*
Let's play the minesweeper game, You are given a 2D char matrix representing the game board.
'M' represents an unrevealed mine, 'E' represents an unrevealed empty square,
'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines,
digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'),
return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed,
then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed,
then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.

### Example
https://leetcode.com/problems/minesweeper/

 */

import java.util.*;

public class M_Array_529 {

    private final char UNREVEALED_MINE = 'M';
    private final char UNREVEALED_EMPTY = 'E';
    private final char MINE = 'X';
    private final char BLANK = 'B';

    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    // DFS, 0ms
    private char[][] updateBoard(char[][] board, int[] click) {
        int y = click[0];
        int x = click[1];
        char cell = board[y][x];

        if (cell == UNREVEALED_MINE) {
            board[y][x] = MINE;
        } else if (cell == UNREVEALED_EMPTY) {
            revealAdjacentBlank(board, y, x);
        }

        return board;
    }

    private void revealAdjacentBlank(char[][] board, int y, int x) {
        if (y < 0 || y >= board.length || x < 0 || x >= board[0].length) {
            return;
        }

        if (board[y][x] != UNREVEALED_EMPTY) {
            return;
        }

        int mineCount = countSurroundingMine(board, y, x);

        if (mineCount != 0) {
            board[y][x] = (char) ('0' + mineCount);
            return;
        }

        board[y][x] = BLANK;

        for (int[] direction : directions) {
            revealAdjacentBlank(board, y + direction[0], x + direction[1]);
        }
    }

    private int countSurroundingMine(char[][] board, int y, int x) {
        int count = 0;

        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (0 <= i && i < board.length && 0 <= j && j < board[0].length && board[i][j] == UNREVEALED_MINE) {
                    count++;
                }
            }
        }

        return count;
    }

    // BFS, 3ms
    private char[][] updateBoard1(char[][] board, int[] click) {
        final int M = board.length;
        final int N = board[0].length;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(click);

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int y = position[0];
            int x = position[1];
            char cell = board[y][x];

            if (cell == UNREVEALED_MINE) {
                board[y][x] = MINE;
            } else if (cell == UNREVEALED_EMPTY) {
                int mineCount = countSurroundingMine(board, y, x);

                if (mineCount == 0) {
                    board[y][x] = BLANK;

                    for (int[] direction : directions) {
                        int nextY = y + direction[0];
                        int nextX = x + direction[1];

                        if (nextY < 0 || nextY >= M || nextX < 0 || nextX >= N) {
                            continue;
                        }

                        char nextCell = board[nextY][nextX];

                        if (nextCell != UNREVEALED_EMPTY) {
                            continue;
                        }

                        queue.offer(new int[]{nextY, nextX});
                    }
                } else {
                    board[y][x] = (char) ('0' + mineCount);
                }
            }
        }

        return board;
    }

}
