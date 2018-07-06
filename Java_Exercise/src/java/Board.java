import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Board {
    private int dimension;
    private int[][] blocks; // blocks[i][j] = block in row i, column j

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }

        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);

        if (solver.isSolvable()) {
            StdOut.println("Minimum number of moves = " + solver.moves());

            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        } else {
            StdOut.println("No solution possible");
        }
    }

    public Board(int[][] blocks) {
        this.dimension = blocks.length;

        System.arraycopy(blocks, 0, this.blocks, 0, blocks.length);
        for (int i = 0; i < this.dimension; ++i){
            System.arraycopy(blocks[i], 0, this.blocks[i], 0, blocks.length);
        }
    }

    public int dimension() {
        return this.dimension;
    }

    /**
     * Hamming priority function
     *
     * @return The number of blocks in the wrong position, plus the number of moves made so far to
     * get to the search node
     */
    public int hamming() {

    }

    /**
     * Manhattan priority function
     *
     * @return The sum of the Manhattan distances (sum of the vertical and horizontal distance)
     * from the blocks to their goal positions, plus the number of moves made so far to
     * get to the search node.
     */
    public int manhattan() {
    }

    // is this board the goal board (sorted)
    public boolean isGoal() {
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
    }

    // does this board equal y?
    public boolean equals(Object y) {
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
    }

    @Override
    public String toString() {
        return "";
    }
}
