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

### Review:

*/

public class H_Backtrack_37 {

    private final char EMPTY = '.';
    private int SIZE = 9;

    // Backtrack ~10ms
    public void solveSudoku1(char[][] board) {
        solve(board, 0);
    }

    // Backtrack ~10ms
    public void solveSudoku2(char[][] board) {
        solve(board, 0, 0);
    }

    // ~5ms
    public void solveSudoku3(char[][] board) {
        // TODO : https://leetcode.com/problems/sudoku-solver/discuss/15748/Sharing-my-2ms-C++-solution-with-comments-and-explanations./212223
    }

    public void solveSudoku4(char[][] board) {
        // 三个布尔数组 表明 行, 列, 还有 3*3 的方格的数字是否被使用过
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][][] boxUsed = new boolean[3][3][10];
        // 初始化
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                int num = board[row][col] - '0';
                if (1 <= num && num <= 9) {
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;
                }
            }
        }
        // 递归尝试填充数组 
        recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, 0, 0);
    }

    private boolean recusiveSolveSudoku(char[][] board, boolean[][] rowUsed, boolean[][] colUsed, boolean[][][] boxUsed, int row, int col) {
        // 边界校验, 如果已经填充完成, 返回true, 表示一切结束
        if (col == board[0].length) {
            col = 0;
            row++;
            if (row == board.length) {
                return true;
            }
        }
        // 是空则尝试填充, 否则跳过继续尝试填充下一个位置
        if (board[row][col] == '.') {
            // 尝试填充1~9
            for (int num = 1; num <= 9; num++) {
                boolean canUse = !(rowUsed[row][num] || colUsed[col][num] || boxUsed[row / 3][col / 3][num]);
                if (canUse) {
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;

                    board[row][col] = (char) ('0' + num);
                    if (recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1)) {
                        return true;
                    }
                    board[row][col] = '.';

                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    boxUsed[row / 3][col / 3][num] = false;
                }
            }
        } else {
            return recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1);
        }
        return false;
    }

    // true: the board can be solved, false:  the board cannot be solved
    private boolean solve(char[][] board, int row, int column) {
        for (int i = row; i < SIZE; ++i, column = 0) { // !!! whenever, row + 1, reset column to 0
            for (int j = column; j < SIZE; ++j) {

                if (board[i][j] != EMPTY) {
                    continue;
                }

                for (char digit = '1'; digit <= '9'; ++digit) {

                    if (!isDigitAbsent(board, i, j, digit)) {
                        continue;
                    }

                    board[i][j] = digit;

                    if (solve(board, i, j + 1)) {
                        return true;
                    } else {
                        board[i][j] = EMPTY;
                    }
                }
                return false;
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

            for (char digit = '1'; digit <= '9'; ++digit) {

                if (!isDigitAbsent(board, row, col, digit)) {
                    continue;
                }

                board[row][col] = digit;

                if (solve(board, i + 1)) {
                    return true;
                } else {
                    board[row][col] = EMPTY;
                }
            }

            return false;
        }

        return true;
    }

    // true: the digit occurred
    private boolean isDigitAbsent(char[][] board, int row, int column, char digit) {
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
    private boolean isDigitAbsent1(char[][] board, int row, int column, char digit) {
        for (int j = 0; j < SIZE; j++) {
            if (board[row][j] == digit) {
                return false;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (board[i][column] == digit) {
                return false;
            }
        }

        for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
            for (int j = column / 3 * 3; j < column / 3 * 3 + 3; j++) {
                if (board[i][j] == digit) {
                    return false;
                }
            }
        }

        return true;
    }

}