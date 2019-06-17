package algorithm.course.assignment.week4.puzzleTest;

import java.util.*;

public class Board {
    private int emptyBlockI;
    private int emptyBlockJ;
    private final int[][] goalBlocks;
    private final int[][] blocks;    // blocks[i][j] = block in row i, column j

    public static void main(String[] args){
        int n = 2;
        int[][] blocks = new int[n][n];
        blocks[0] = new int[]{0, 2};
        blocks[1] = new int[]{1, 3};

        System.out.println(new Board(blocks).manhattan());
    }

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

        this.goalBlocks = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                this.goalBlocks[i][j] = 1 + i * N + j;
            }
        }
        this.goalBlocks[N - 1][N - 1] = 0;

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
                if (value != 0 && value != this.goalBlocks[i][j]) {
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
     * from the blocks to their goal positions, plus the number of moves made so far to
     * get to the search node.
     */
    public int manhattan() {
        int distance = 0;
        final int N = this.dimension();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {

                int value = this.blocks[i][j];
                if (value != 0 && value != this.goalBlocks[i][j]) {

                    // a[i, j] = N * i + j + 1
                    int goalI = (value -1) / N;
                    int goalJ = (value -1) % N;

                    distance += Math.abs(goalI - i ) + Math.abs(goalJ - j);
                }
            }
        }

        return distance;
    }

    // is this board the goal board
    public boolean isGoal() {
        return this.manhattan() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int N = this.dimension();
        Board twin = null;
        Random random = new Random();

        while (true) {
            int i1 = random.nextInt(N);
            int j1 = random.nextInt(N);
            int i2 = random.nextInt(N);
            int j2 = random.nextInt(N);

            if (this.blocks[i1][j1] * this.blocks[i2][j2] != 0) {
                twin = this.swap(i1, j1, i2, j2);
                break;
            }
        }

        return twin;
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

    private class Neighbors implements Iterable<Board> {
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
