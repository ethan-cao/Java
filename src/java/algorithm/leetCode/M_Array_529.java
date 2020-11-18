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

public class M_Array_529 {

    private final char UNREVEALED_MINE = 'M';
    private final char UNREVEALED_EMPTY = 'E';
    private final char MINE = 'X';
    private final char BLANK = 'B';

    private final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    private char[][] updateBoard(char[][] board, int[] click) {
        char toBeRevealed = board[click[0]][click[1]];

        if (toBeRevealed == UNREVEALED_MINE) {
            board[click[0]][click[1]] = MINE;
        } else if (toBeRevealed == UNREVEALED_EMPTY) {
            checkSurroundings(board, click[0], click[1]);
        }

        return board;
    }

    // check UNREVEALED_EMPTY block
    private void checkSurroundings(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != UNREVEALED_EMPTY) {
            return;
        }

        int mineCount = countSurroundingMine(board, x, y);

        if (mineCount > 0) {
            board[x][y] = (char) ('0' + mineCount);  // !!! convert int to char
        } else {
            board[x][y] = BLANK;

            for (int[] vector : DIRECTIONS) {
                checkSurroundings(board, x + vector[0], y + vector[1]);
            }
        }
    }

    private int countSurroundingMine(char[][] board, int x, int y) {
        int mineCount = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (0 <= i && i < board.length && 0 <= j && j < board[0].length && board[i][j] == UNREVEALED_MINE)
                    mineCount++;
            }
        }

        return mineCount;
    }

    // BFS
    // Time: O(N)
    private char[][] updateBoard1(char[][] board, int[] click) {


        return board;
    }
}
