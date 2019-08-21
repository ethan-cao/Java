package algorithm.leetCode;

/*
Given a string s, find the longest palindromic subsequence's length in s.
You may assume that the maximum length of s is 1000.

### Example
"bbbab" -> 4, One possible longest palindromic subsequence is "bbbb".
"cbbd" -> 2, One possible longest palindromic subsequence is "bb".



Related: 5, 647
*/


public class M_DP_516 {
    public static void main(String... args) {
        System.out.println(longestPalindromeSubseq("")); //
        System.out.println(longestPalindromeSubseq("a")); //
        System.out.println(longestPalindromeSubseq("aa")); //
        System.out.println(longestPalindromeSubseq("bbbab")); // 4
        System.out.println(longestPalindromeSubseq("cbbd")); // 2
    }

    public static int longestPalindromeSubseq(String s) {
        return 1;
    }

}
