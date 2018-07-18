import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solver {
    final private boolean isSolvable;
    private int moves;
    final private List<Board> solutions = new ArrayList<>();

    private class SearchNode {
        final private Board board;
        final private int move;
        final private SearchNode predecessor;
        final private int priority;

        SearchNode(Board board, SearchNode predecessor, int move) {
            this.board = board;
            this.predecessor = predecessor;
            this.move = move;
            this.priority = board.manhattan() + move;
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

//        int n = in.readInt();
        int n = 3;
        int[][] blocks = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }

        /*
            0  1  3
            4  2  5
            7  8  6
        */

        blocks[0] = new int[]{0, 1, 3};
        blocks[1] = new int[]{4, 2, 5};
        blocks[2] = new int[]{7, 8, 6};

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

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        if (initial.twin().isGoal()) {
            this.isSolvable = false;
        } else {
            final MinPQ<SearchNode> searchNodes = new MinPQ<>();
            SearchNode initialNode = new SearchNode(initial, null, 0);
            searchNodes.insert(initialNode);

            while (true) {
                SearchNode minNode = searchNodes.delMin();
                this.solutions.add(minNode.board);

                if (minNode.board.isGoal()) {
                    this.isSolvable = true;
                    break;
                }

                for (Board board : minNode.board.neighbors()) {
                    SearchNode neighbor = new SearchNode(board, minNode, minNode.move + 1);
                    // don't enqueue a neighbor if its board is the same as the board of the predecessor search node.
                    if (!neighbor.equals(minNode.board)) {
                        searchNodes.insert(neighbor);
                    }
                }
            }

        }
    }

    /**
     * not solvable when
     * we modify the initial board by swapping any pair of blocks (the blank square is not a block)
     * and it leads to the goal board
     */
    public boolean isSolvable() {
        return this.isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return this.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!this.isSolvable) {
            return null;
        }

        return this.solutions;
    }
}
