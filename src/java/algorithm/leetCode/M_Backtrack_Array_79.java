package algorithm.leetCode;

/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once.

### Example
board = [   ['A','B','C','E'],
            ['S','F','C','S'],
            ['A','D','E','E']    ]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

*/

public class M_Backtrack_Array_79 {

    public static void main(String... args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        System.out.println(exist(board, "ABCCED")); // T
        System.out.println(exist(board, "SEE"));    // T
        System.out.println(exist(board, "ABCB"));   // F

        char[][] board1 = {{'a'}};
        System.out.println(exist(board1, "a")); // T
        System.out.println(exist(board1, "ab")); // F

        char[][] board2 = {{'a', 'b'}};
        System.out.println(exist(board2, "ba")); // T
    }

    // BFS + Backtrack
    // Time: O(M * N * 4^L) where M*N is the size of the board and we have 4^L for each cell because of the recursion
    // Space: O(L) where L is the length of the word
    public static boolean exist(char[][] board, String word) {
//        boolean[] used = new boolean[board.length * board[0].length];  // 1D array does not save space
        boolean[][] used = new boolean[board.length][board[0].length];

        for (int row = 0; row < board.length; ++row) {
            for (int column = 0; column < board[row].length; ++column) {
                if (searchWord(board, row, column, word, 0, used)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean searchWord(char[][] board, int row, int column, String word, int matchCount, boolean[][] used) {
        if (matchCount == word.length()) {
            return true;
        }

        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length) {
            return false;
        }

        if (board[row][column] != word.charAt(matchCount) || used[row][column]) { // order of check does not affect time
            return false;
        }

        used[row][column] = true;

        // this works, just slow 8ms
//        int[][] vectors = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//        for (int[] vector : vectors) {
//            if (searchWord(board, row + vector[0], column + vector[1], word, matchCount + 1, used)) {
//                return true;
//            }
//        }

        // this works and faster, 4ms
        if (searchWord(board, row, column + 1, word, matchCount + 1, used) ||
                searchWord(board, row, column + -1, word, matchCount + 1, used) ||
                searchWord(board, row + 1, column, word, matchCount + 1, used) ||
                searchWord(board, row + -1, column, word, matchCount + 1, used)) {
            return true;
        }

        used[row][column] = false;  // backtrack

        return false;
    }

}