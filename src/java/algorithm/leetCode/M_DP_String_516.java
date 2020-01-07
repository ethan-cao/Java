package algorithm.leetCode;

/*
Given a string s, find the longest palindromic subsequence's length in s.
You may assume that the maximum length of s is 1000.

### Example
"bbbab" -> 4, One possible longest palindromic subsequence is "bbbb".
"cbbd" -> 2, One possible longest palindromic subsequence is "bb".
*/

public class M_DP_String_516 {

    public static void main(String... args) {
        System.out.println(longestPalindromeSubseq0("")); // 0
        System.out.println(longestPalindromeSubseq0("a")); // 1
        System.out.println(longestPalindromeSubseq0("aa")); // 2
        System.out.println(longestPalindromeSubseq0("aaa")); // 3
        System.out.println(longestPalindromeSubseq0("bbbab")); // 4
        System.out.println(longestPalindromeSubseq0("cbbd")); // 2
    }

    // DP, use tabulation to analyze
    public static int longestPalindromeSubseq0(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // longestLength[i][j] : longest Palindrome Subsequence for substring(i, j+1)
        int[][] longestLength = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            longestLength[i][i] = 1;

            for (int j = i + 1; j < s.length(); ++j) {

                if (s.charAt(i) == s.charAt(j)) {
                    longestLength[i][j] = longestLength[i + 1][j - 1] + 2;
                    // longestLength[i + 1][j - 1] exclude the 2 chars that matches
                } else {
                    longestLength[i][j] = Math.max(longestLength[i][j - 1], longestLength[i + 1][j]);
                }
            }
        }

        return longestLength[0][s.length() - 1];
    }

}
