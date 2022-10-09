package algorithm.course.assignment.week4;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

// https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php
// Write a program to solve the 8-puzzle problem (and its natural generalizations) using the A* search algorithm.
final public class Board {
    private int emptyBlockI;
    private int emptyBlockJ;
    private final int[][] blocks;    // blocks[i][j] = block in row i, column j

    // constructor receives an n-by-n (2 ≤ n < 128) array containing the n^2 integers between 0 and n^2 − 1,
    // where 0 represents the blank square
    public Board(int[][] blocks) {
        int N = blocks.length;

        this.blocks = new int[N][N];
        for (int i = 0; i < N; ++i) {
            this.blocks[i] = new int[N];
            for (int j = 0; j < N; ++j) {
                this.blocks[i][j] = blocks[i][j];

                if (this.blocks[i][j] == 0) {
                    this.emptyBlockI = i;
                    this.emptyBlockJ = j;
                }
            }
        }
    }

    public int dimension() {
        return this.blocks.length;
    }

    /**
     * Hamming function
     * heuristics
     *
     * @return The number of blocks in the wrong position, plus the number of moves made so far to
     * get to the search node
     */
    public int hamming() {
        int numberOfWrongPosition = 0;

        for (int i = 0; i < this.dimension(); ++i) {
            for (int j = 0; j < this.dimension(); ++j) {
                int value = this.blocks[i][j];
                if (value != 0 && value != this.getGoalValue(i, j)) {
                    numberOfWrongPosition++;
                }
            }
        }

        return numberOfWrongPosition;
    }

    /**
     * Manhattan distance function
     * heuristics
     *
     * @return The sum of the Manhattan distances (sum of the vertical and horizontal distance)
     * from the blocks to their goal positions
     */
    public int manhattan() {
        int distance = 0;
        final int N = this.dimension();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {

                int value = this.blocks[i][j];
                if (value != 0) {
                    // a[i, j] = N * i + j + 1
                    int goalI = (value - 1) / N;
                    int goalJ = (value - 1) % N;

                    distance += Math.abs(goalI - i) + Math.abs(goalJ - j);
                }
            }
        }

        return distance;
    }

    // is this board the goal board
    public boolean isGoal() {
        return this.manhattan() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks (empty block does not count)
    // A method in an immutable type must return the same value given the same argument
    public Board twin() {
        int i1 = 0;
        int j1 = this.emptyBlockJ == 0 ? 1 : 0;
        int i2 = 1;
        int j2 = this.emptyBlockJ == 0 ? 1 : 0;

        return this.swap(i1, j1, i2, j2);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }

        Board board = (Board) y;

        if (board.dimension() != this.dimension()) {
            return false;
        }

        for (int i = 0; i < this.dimension(); ++i) {
            for (int j = 0; j < this.dimension(); ++j) {
                if (board.blocks[i][j] != this.blocks[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private int getGoalValue(int i, int j) {
        int N = this.dimension();

        if (i == N - 1 && j == N - 1) {
            return 0;
        }

        return 1 + i * N + j;
    }

    private Board swap(int I1, int J1, int I2, int J2) {
        if (this.blocks == null ||
                I1 < 0 || I1 > this.dimension() - 1 ||
                J1 < 0 || J1 > this.dimension() - 1 ||
                I2 < 0 || I2 > this.dimension() - 1 ||
                J2 < 0 || J2 > this.dimension() - 1) {
            return null;
        }

        Board newBoard = new Board(this.blocks);
        int temp = newBoard.blocks[I1][J1];
        newBoard.blocks[I1][J1] = newBoard.blocks[I2][J2];
        newBoard.blocks[I2][J2] = temp;

        if (newBoard.blocks[I1][J1] == 0) {
            newBoard.emptyBlockI = I1;
            newBoard.emptyBlockJ = J1;
        }
        if (newBoard.blocks[I2][J2] == 0) {
            newBoard.emptyBlockI = I2;
            newBoard.emptyBlockJ = J2;
        }

        return newBoard;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new Neighbors(this);
    }

    private static class Neighbors implements Iterable<Board> {
        //        private int current = -1;
        final private Queue<Board> neighbors = new ArrayDeque<>();

        private Neighbors(Board board) {
            int N = board.dimension();
            if (N <= 1) {
                return;
            }

            int neighborI = board.emptyBlockI - 1;
            int neighborJ = board.emptyBlockJ;
            if (neighborI >= 0 && neighborI < N) {
                this.neighbors.offer(board.swap(board.emptyBlockI, board.emptyBlockJ, neighborI, neighborJ));
            }

            neighborI = board.emptyBlockI + 1;
            if (neighborI >= 0 && neighborI < N) {
                this.neighbors.offer(board.swap(board.emptyBlockI, board.emptyBlockJ, neighborI, neighborJ));
            }

            neighborI = board.emptyBlockI;
            neighborJ = board.emptyBlockJ - 1;
            if (neighborJ >= 0 && neighborJ < N) {
                this.neighbors.offer(board.swap(board.emptyBlockI, board.emptyBlockJ, neighborI, neighborJ));
            }

            neighborJ = board.emptyBlockJ + 1;
            if (neighborJ >= 0 && neighborJ < N) {
                this.neighbors.offer(board.swap(board.emptyBlockI, board.emptyBlockJ, neighborI, neighborJ));
            }
        }

        @Override
        public Iterator<Board> iterator() {
            return new Iterator<Board>() {
                @Override
                public boolean hasNext() {
                    return !Neighbors.this.neighbors.isEmpty();
//                    return !Neighbors.this.neighbors.isEmpty() &&
//                            Neighbors.this.current < Neighbors.this.neighbors.size() - 1;
                }

                @Override
                public Board next() {
                    if (this.hasNext()) {
//                        return Neighbors.this.neighbors.get(++Neighbors.this.current);
                        return Neighbors.this.neighbors.remove();
                    } else {
                        throw new NoSuchElementException();
                    }
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

    }

    @Override
    public String toString() {
        final int N = this.dimension();
        final StringBuilder sb = new StringBuilder();
        sb.append(N).append("\n");

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                sb.append(" " + this.blocks[i][j]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
