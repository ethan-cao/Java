package algorithm.leetCode;

/*
Given a string s, find the longest palindromic subsequence's length in s.
You may assume that the maximum length of s is 1000.

1 <= s.length <= 1000
s consists only of lowercase English letters.

### Example
"bbbab" -> 4, One possible longest palindromic subsequence is "bbbb".
"cbbd" -> 2, One possible longest palindromic subsequence is "bb".

*/

public class M_DP_String_516 {

    public static void main(String... args) {
        System.out.println(longestPalindromeSubseq("a")); // 1
        System.out.println(longestPalindromeSubseq("aa")); // 2
        System.out.println(longestPalindromeSubseq("aaa")); // 3
        System.out.println(longestPalindromeSubseq("bbbab")); // 4
        System.out.println(longestPalindromeSubseq("cbbd")); // 2
    }

    // DP, use tabulation to analyze
    public static int longestPalindromeSubseq(String s) {
        // longestLength[i][j]: longest Palindrome Subsequence for substring(left, right)
        int L = s.length();
        int[][] longestLength = new int[L][L];

        for (int left = L - 1; left >= 0; --left) {
            longestLength[left][left] = 1;

            for (int right = left + 1; right < L; ++right) {

                if (s.charAt(left) == s.charAt(right)) {
                    // 2 more chars than longestLength[left + 1][right - 1]
                    longestLength[left][right] = 2 + longestLength[left + 1][right - 1];
                } else {
                    longestLength[left][right] = Math.max(longestLength[left + 1][right], longestLength[left][right - 1]);
                }
            }
        }

        return longestLength[0][L - 1];
    }

    // DP, recursive
    public static int longestPalindromeSubseq1(String s) {
        return 1;
    }

}