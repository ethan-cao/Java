package algorithm.leetCode;

/*
Write a program to solve a Sudoku(数独) puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.

### Example
https://leetcode.com/problems/sudoku-solver/

*/

public class H_Backtrack_37 {

    private final char EMPTY = '.';
    private int SIZE = 9;

    public void solveSudoku(char[][] board) {
        // Backtrack, 12ms
        solve(board);

        // Backtrack, 5ms
        solve(board, 0);
    }

    // true: the board can be solved, false:  the board cannot be solved
    private boolean solve(char[][] board) {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {

                if (board[i][j] != EMPTY) {
                    continue;
                }

                for (char digit = '1'; digit <= '9'; ++digit) {

                    // if digit already occurred, skip
                    if (!checkDigitOccurrence(board, i, j, digit)) {
                        continue;
                    }

                    // Try filling board[i][j] with digit
                    board[i][j] = digit;

                    // backtrack
                    if (solve(board)) {
                        // fill board[i][j] with digit, if the path works, then it is the one solution
                        return true;
                    } else {
                        // if not valid, backtrack
                        board[i][j] = EMPTY;
                    }
                }

                // !!! after try all possible digit, true is not return, then it is not possible
                return false;
            }
        }

        // this is the case where SIZE = 0; without board, just return true
        return true;
    }

    private boolean checkDigitOccurrence(char[][] board, int row, int column, char digit) {
        for (int i = 0; i < SIZE; ++i) {

            // occur exactly once in each column
            if (board[i][column] == digit) {
                return false;
            }

            // occur exactly once in each row.
            if (board[row][i] == digit) {
                return false;
            }

            int localRowOrigin = 3 * (row / 3);  //!!! 3 * (row / 3) != row
            int localColumnOrigin = 3 * (column / 3);
            int localRow = i / 3;
            int localColumn = i % 3;

            // occur exactly once in each of the 9 3x3 sub-boxes
            if (board[localRowOrigin + localRow][localColumnOrigin + localColumn] == digit) {
                return false;
            }
        }

        return true;
    }

    // checkDigitOccurrence1 is faster than checkDigitOccurrence1
    // use 3 different loop, increase the change of terminating loop early
    private boolean checkDigitOccurrence1(char[][] board, int row, int col, char num) {
        for (int j = 0; j < SIZE; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
            for (int j = col / 3 * 3; j < col / 3 * 3 + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean solve(char[][] board, int start) {
        // !!! Actually this does not affect performance that much
        for (int i = start; i < SIZE * SIZE; i++) {
            int row = i / 9;
            int col = i % 9;

            if (board[row][col] != EMPTY) {
                continue;
            }

            for (char digit = '1'; digit <= '9'; digit++) {
                if (checkDigitOccurrence(board, row, col, digit)) {
                    board[row][col] = digit;

                    if (solve(board, i + 1)) {
                        return true;
                    } else {
                        board[row][col] = EMPTY;
                    }
                }
            }

            return false;
        }

        return true;
    }

}