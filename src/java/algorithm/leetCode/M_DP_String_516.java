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
//        System.out.println(longestPalindromeSubseq("")); // 0
//        System.out.println(longestPalindromeSubseq("a")); // 1
//        System.out.println(longestPalindromeSubseq("aa")); // 2
        System.out.println(longestPalindromeSubseq("aaa")); // 3
//        System.out.println(longestPalindromeSubseq("bbbab")); // 4
//        System.out.println(longestPalindromeSubseq("cbbd")); // 2
    }

    // DP, use tabulation to analyze
    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // longestLength[i][j]: longest Palindrome Subsequence for substring(left, right)
        int[][] longestLength = new int[s.length()][s.length()];

        for (int left = s.length() - 1; left >= 0; --left) {
            longestLength[left][left] = 1;

            for (int right = left + 1; right < s.length(); ++right) {

                if (s.charAt(left) == s.charAt(right)) {
                    // 2 more chars than longestLength[left + 1][right - 1]
                    longestLength[left][right] = 2 + longestLength[left + 1][right - 1];
                } else {
                    longestLength[left][right] = Math.max(longestLength[left + 1][right], longestLength[left][right - 1]);
                }
            }
        }

        return longestLength[0][s.length() - 1];
    }

}