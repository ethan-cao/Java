public class Solver {
    public static void main(String[] args) {

    }

    public Solver(Board initial) {

    }

    //  is the initial board solvable?
    public boolean isSolvable() {

    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!this.isSolvable()){
            return -1;
        }


    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!this.isSolvable()){
           return null;
        }


    }

}
