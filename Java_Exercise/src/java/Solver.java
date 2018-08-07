import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;
import java.util.List;

final public class Solver {
    private final boolean isSolvable;
    private int moves;
    private final List<Board> solutions = new LinkedList<>();

    private static class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int move;
        private final SearchNode predecessor;
        private final int priority;

        SearchNode(Board board, SearchNode predecessor, int move) {
            this.board = board;
            this.predecessor = predecessor;
            this.move = move;
            // priority is manhattan distance plus the number of moves made so far to get to the search node.
            this.priority = board.manhattan() + move;
        }

        @Override
        public int compareTo(SearchNode searchNode) {
            if (this.priority == searchNode.priority) {
                return 0;
            } else if (this.priority > searchNode.priority) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        if (initial.twin().isGoal()) {
            this.isSolvable = false;
        } else {
            SearchNode initialNode = new SearchNode(initial, null, 0);
            MinPQ<SearchNode> searchNodes = new MinPQ<>();
            searchNodes.insert(initialNode);


            while (true) {
                SearchNode minNode = searchNodes.delMin();
                searchNodes = new MinPQ<>();
                this.solutions.add(minNode.board);

                if (minNode.board.isGoal()) {
                    this.isSolvable = true;
                    this.moves = minNode.move;
                    break;
                }

                if (minNode.board.twin().isGoal()) {
                    this.isSolvable = false;
                    break;
                }

                for (Board board : minNode.board.neighbors()) {
                    SearchNode neighbor = new SearchNode(board, minNode, minNode.move + 1);

                    // don't enqueue a neighbor if its board is the same as the board of the predecessor search node.
                    if (minNode.predecessor == null) {
                        searchNodes.insert(neighbor);
                    } else {
                        if (!neighbor.board.equals(minNode.predecessor.board)) {
                            searchNodes.insert(neighbor);
                        }
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
        if (!this.isSolvable) {
            return -1;
        }

        return this.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!this.isSolvable) {
            return null;
        }

        return this.solutions;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();

//        int n = 3;

        int[][] blocks = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }

//        blocks[0] = new int[]{1, 2, 3};
//        blocks[1] = new int[]{0, 7, 6};
//        blocks[2] = new int[]{5, 4, 8};

        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);


        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()){
                StdOut.println(board);
            }
        }

        System.out.println(solver.moves());
    }
}
