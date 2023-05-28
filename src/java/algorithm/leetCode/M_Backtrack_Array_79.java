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
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };

        System.out.println(exist(board, "ABCCED")); // T
        System.out.println(exist(board, "SEE")); // T
        System.out.println(exist(board, "ABCB")); // F

        char[][] board1 = { { 'a' } };
        System.out.println(exist(board1, "a")); // T
        System.out.println(exist(board1, "ab")); // F

        char[][] board2 = { { 'a', 'b' } };
        System.out.println(exist(board2, "ba")); // T
    }

    // BFS + Backtrack
    // Time: O(M * N * 4^L) where M*N is the size of the board and we have 4^L for each cell because of the recursion
    // Space: O(L) where L is the length of the word
    private static int[][] DIRECTIONS = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public static boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];

        // !!! start from all the positions
        for (int y = 0; y < board.length; ++y) {
            for (int x = 0; x < board[0].length; ++x) {
                if (search(board, y, x, word, 0, used)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean search(char[][] board, int y, int x, String word, int idx, boolean[][] isUsed) {
        if (y < 0 || y >= board.length || x < 0 || x >= board[0].length) {
            return false;
        }

        if (isUsed[y][x]) {
            return false;
        }

        if (idx < word.length() && board[y][x] != word.charAt(idx)) {
            return false;
        }

        if (idx == word.length() - 1) {
            return true;
        }

        isUsed[y][x] = true;

        for (int[] DIRECTION : DIRECTIONS) {
            int nextY = y + DIRECTION[0];
            int nextX = x + DIRECTION[1];

            if (search(board, word, idx + 1, nextY, nextX, isUsed)) {
                return true;
            }
        }

        isUsed[y][x] = false;

        return false;
    }

}