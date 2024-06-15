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
        System.out.println(longestPalindromeSubseq_1("a")); // 1
        System.out.println(longestPalindromeSubseq_1("aa")); // 2
        System.out.println(longestPalindromeSubseq_1("aaa")); // 3
        System.out.println(longestPalindromeSubseq_1("bbbab")); // 4
        System.out.println(longestPalindromeSubseq_1("cbbd")); // 2
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative
    public int longestPalindromeSubseq(String s) {
        int L = s.length();
        int[][] counts = new int[L][L];

        for (int end = 0; end < L; ++end) {
            for (int start = end; start >= 0; --start) {

                if (start == end) {
                    // BASE
                    counts[start][end] = 1;
                } else {
                    // TRANSFROM
                    if (s.charAt(start) == s.charAt(end)) {
                        if (start + 1 <= end - 1) {
                            counts[start][end] = 2 + counts[start + 1][end - 1];
                        } else {
                            counts[start][end] = 2;
                        }
                    } else {
                        counts[start][end] = Math.max(counts[start + 1][end], counts[start][end - 1]);
                    }
                }

            }
        }

        return counts[0][L - 1];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative, condensed space
    // 70 ms
    public static int longestPalindromeSubseq_5(String s) {
        int L = s.length();

        int[] counts = new int[L];
        int[] preCounts = new int[L];

        // since the current row is derived from previous row
        // iterate the start backwards
        for (int start = L - 1; start >= 0; --start) {
            counts[start] = 1;

            for (int end = start + 1; end < L; ++end) {

                if (s.charAt(start) == s.charAt(end)) {
                    counts[end] = preCounts[end - 1] + 2;
                } else {
                    counts[end] = Math.max(preCounts[end], counts[end - 1]);
                }
            }

            preCounts = counts;
            counts = new int[L];
        }

        return preCounts[L - 1];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, recursive, TLE
    public static int longestPalindromeSubseq_1(String s) {
        return getSequence(s, 0, s.length() - 1);
    }

    private static int getSequence(String s, int start, int end) {
        if (start == end) {
            return 1;
        }

        if (start > end) {
            return 0;
        }

        if (s.charAt(start) == s.charAt(end)) {
            return 2 + getSequence(s, start + 1, end - 1);
        } else {
            return Math.max(getSequence(s, start + 1, end), getSequence(s, start, end - 1));
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, recursive, cache
    // 28ms
    public int longestPalindromeSubseq_2(String s) {
        final int L = s.length();
        Integer[][] memo = new Integer[L][L];

        return getSequence(s, 0, s.length() - 1, memo);
    }

    private int getSequence(String s, int start, int end, Integer[][] memo) {
        if (start == end) {
            return 1;
        }

        if (start > end) {
            return 0;
        }

        if (memo[start][end] != null) {
            return memo[start][end];
        }

        if (s.charAt(start) == s.charAt(end)) {
            memo[start][end] = 2 + getSequence(s, start + 1, end - 1, memo);
        } else {
            memo[start][end] = Math.max(getSequence(s, start + 1, end, memo), getSequence(s, start, end - 1, memo));
        }

        return memo[start][end];
    }
}