package algorithm.leetCode;

/*
According to the Wikipedia's article: "The Game of Life, also known simply as Life,
is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
Each cell interacts with its 8 neighbors (horizontal, vertical, diagonal) using the following four rules
(taken from the above Wikipedia article):

Any live cell with fewer than 2 live neighbors dies, as if caused by under-population.
Any live cell with 2 or 3 live neighbors lives on to the next generation.
Any live cell with more than 3 live neighbors dies, as if by over-population..
Any dead cell with exactly 3 live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state.
The next state is created by applying the above rules simultaneously to every cell in the current state,
where births and deaths occur simultaneously.

### Example
Input:
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output:
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
*/

import java.util.Arrays;

public class M_Array_289 {

    public static void main(String... args) {
        int[][] board1 = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        gameOfLife(board1);
        for (int[] a : board1) {
            System.out.println(Arrays.toString(a));
        }
    }

    public static void gameOfLife(int[][] board) {
        int[][] nextState = new int[board.length][board[0].length];

        for (int i = 0; i < nextState.length; ++i) {
            for (int j = 0; j < nextState[0].length; ++j) {

                int liveNeighbour = countLiveNeighbours(board, i, j);

                if (isLive(board, i, j)) {
                    if (liveNeighbour < 2 || liveNeighbour > 3) {
                        nextState[i][j] = 0;
                    } else {
                        nextState[i][j] = 1;
                    }
                } else {
                    if (liveNeighbour == 3) {
                        nextState[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < nextState.length; i++) {
            System.arraycopy(nextState[i], 0, board[i], 0, nextState[i].length);
        }
    }

    private static int countLiveNeighbours(int[][] board, int i, int j) {
        int count = 0;

        if (i > 0) {
            if (isLive(board, i - 1, j)) count++;

            if (j > 0) {
                if (isLive(board, i - 1, j - 1)) count++;
            }

            // !!! use separate if, not if-else
            if (j < board[0].length - 1) {
                if (isLive(board, i - 1, j + 1)) count++;
            }
        }

        if (i < board.length - 1) {
            if (isLive(board, i + 1, j)) count++;

            if (j < board[0].length - 1) {
                if (isLive(board, i + 1, j + 1)) count++;
            }
            if (j > 0) {
                if (isLive(board, i + 1, j - 1)) count++;
            }
        }

        if (j > 0) {
            if (isLive(board, i, j - 1)) count++;
        }

        if (j < board[0].length - 1) {
            if (isLive(board, i, j + 1)) count++;

        }

        return count;
    }

    private static boolean isLive(int[][] board, int i, int j) {
        return board[i][j] == 1;
    }

}