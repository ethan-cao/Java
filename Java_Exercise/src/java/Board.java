import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Board {
    private int emptyBlockI;
    private int emptyBlockJ;
    private final int[][] goalBlocks;

    private final int[][] blocks;    // blocks[i][j] = block in row i, column j
    private int move = 0;
    private int hammingPriority;
    private int manhattanPriority;

    public static void main(String[] args) {
        int n = 3;
        int[][] blocks = new int[n][n];
        blocks[0] = new int[]{0, 1, 3};
        blocks[1] = new int[]{4, 2, 5};
        blocks[2] = new int[]{7, 8, 6};

        Board initial = new Board(blocks);

        System.out.print(initial);
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
                    emptyBlockI = i;
                    emptyBlockJ = j;
                }
            }
        }

        this.goalBlocks = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                this.goalBlocks[i][j] = 1 + i + j;
            }
        }
        this.goalBlocks[N - 1][N - 1] = 0;

    }

    public int dimension() {
        return this.blocks.length;
    }

    /**
     * Hamming priority function
     *
     * @return The number of blocks in the wrong position, plus the number of moves made so far to
     * get to the search node
     */
    public int hamming() {
        int numberOfWrongDistance = 0;

        for (int i = 0; i < this.dimension(); ++i) {
            for (int j = 0; j < this.dimension(); ++j) {
                if (this.blocks[i][j] != this.goalBlocks[i][j]) {
                    numberOfWrongDistance++;
                }
            }
        }

        return numberOfWrongDistance;
    }

    /**
     * Manhattan priority function
     *
     * @return The sum of the Manhattan distances (sum of the vertical and horizontal distance)
     * from the blocks to their goal positions, plus the number of moves made so far to
     * get to the search node.
     */
    public int manhattan() {
        int distance = this.move;
        final int N = this.dimension();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {

                int value = this.blocks[i][j];
                if (value != this.goalBlocks[i][j]) {
                    int goalI = value / N;
                    int goalJ = value % N - 1;

                    distance += Math.abs((goalI + goalJ) - (i + j));
                }
            }
        }

        return distance;
    }

    // is this board the goal board (sorted)
    public boolean isGoal() {
        return this.equals(this.goalBlocks);
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int N = this.dimension();
        Board twin = null;

        for (int i = 1; i < N; ++i) {
            if (this.blocks[0][0] * this.blocks[0][i] != 0) {
                twin = this.swap(0, 0, 0, i);
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

        return newBoard;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new Neighbors(this);
    }

    private class Neighbors implements Iterable<Board> {
        private int current = -1;
        private List<Board> neighbors = new ArrayList<>();

        private Neighbors(Board board) {
            if (board.dimension() <= 0) {
                return;
            }

            int neighborI = board.emptyBlockI - 1;
            int neighborJ = board.emptyBlockJ;
            if (neighborI >= 0 && neighborI < board.dimension()) {
                this.neighbors.add(board.swap(board.emptyBlockI, board.emptyBlockJ, neighborI, neighborJ));
            }

            neighborI = board.emptyBlockI + 1;
            if (neighborI >= 0 && neighborI < board.dimension()) {
                this.neighbors.add(board.swap(board.emptyBlockI, board.emptyBlockJ, neighborI, neighborJ));
            }

            neighborI = board.emptyBlockI;
            neighborJ = board.emptyBlockJ - 1;
            if (neighborJ >= 0 && neighborJ < board.dimension()) {
                this.neighbors.add(board.swap(board.emptyBlockI, board.emptyBlockJ, neighborI, neighborJ));
            }

            neighborJ = board.emptyBlockJ + 1;
            if (neighborJ >= 0 && neighborJ < board.dimension()) {
                this.neighbors.add(board.swap(board.emptyBlockI, board.emptyBlockJ, neighborI, neighborJ));
            }
        }

        @Override
        public Iterator<Board> iterator() {
            return new Iterator<Board>() {
                @Override
                public boolean hasNext() {
                    return !Neighbors.this.neighbors.isEmpty() &&
                            Neighbors.this.current < Neighbors.this.neighbors.size() - 1;
                }

                @Override
                public Board next() {
                    if (this.hasNext()) {
                        return Neighbors.this.neighbors.get(++Neighbors.this.current);
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
