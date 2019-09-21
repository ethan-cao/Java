package algorithm.course.assignment.week4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.Deque;

final public class Solver {
    private final boolean isSolvable;
    private int moves;
    private SearchNode goal;

    private static class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int move;
        private final SearchNode predecessor;
        private final int priority;

        SearchNode(Board board, SearchNode predecessor, int move) {
            this.board = board;
            this.predecessor = predecessor;
            this.move = move;
            this.priority = board.manhattan() + move; //manhattan distance plus the number of moves made so far to get to the search node.
        }

        @Override
        public int compareTo(SearchNode searchNode) {
            if (this.priority > searchNode.priority) {
                return 1;
            }

            if (this.priority < searchNode.priority) {
                return -1;
            }

            return 0;
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        if (initial.twin().isGoal()) {
            this.isSolvable = false;
        } else {
            MinPQ<SearchNode> searchNodes = new MinPQ<>();
            SearchNode initialNode = new SearchNode(initial, null, 0);
            searchNodes.insert(initialNode);

            while (true) {
                SearchNode minNode = searchNodes.delMin();
                // no need to clean searchNodes

                if (minNode.board.isGoal()) {
                    this.isSolvable = true;
                    this.moves = minNode.move;
                    this.goal = minNode;
                    break;
                }

                if (minNode.board.twin().isGoal()) {
                    this.isSolvable = false;
                    this.moves = -1;
                    break;
                }

                for (Board board : minNode.board.neighbors()) {
                    SearchNode neighbor = new SearchNode(board, minNode, minNode.move + 1);

                    // don't enqueue a neighbor if its board is the same as the board of the predecessor search node.
                    if (minNode.predecessor != null && !board.equals(minNode.predecessor.board)) {
                        searchNodes.insert(neighbor);
                    }

                    if (minNode.predecessor == null) {
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

        Deque<Board> solution = new ArrayDeque<>();
        SearchNode node = this.goal;

        while (true){
            solution.push(node.board);
            node = node.predecessor;

            if (node == null){
                break;
            }
        }

        return solution;
    }

    public static void main(String[] args) {
//        In in = new In(args[0]);
//        int n = in.readInt();

        int n = 3;

        int[][] blocks = new int[n][n];

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                blocks[i][j] = in.readInt();
//            }
//        }

        blocks[0] = new int[]{1, 0, 2};
        blocks[1] = new int[]{7, 5, 4};
        blocks[2] = new int[]{8, 6, 3};

        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);

//        System.out.println("--------------------");

        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }

    }
}
