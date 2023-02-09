package algorithm.leetCode;

/*
Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none)
deleted without changing the relative order of the remaining characters.
(eg, "ace" is a subsequence of "abcde" while "aec" is not).

A common subsequence of two strings is a subsequence that is common to both strings.
If there is no common subsequence, return 0.

1 <= text1.length <= 1000
1 <= text2.length <= 1000
The input strings consist of lowercase English characters only.

### Example
text1 = "abcde", text2 = "ace"  => 3
text1 = "abc", text2 = "abc"    => 3
text1 = "abc", text2 = "def"    => 0

*/

import java.util.Arrays;

public class M_DP_String_1143 {

    public static void main(String... args) {
        System.out.println(longestCommonSubsequence("abcde", "ace")); // 3
        System.out.println(longestCommonSubsequence("abvwerbwbc", "avrabc")); // 5
    }

    // DP, recursive
    // 49ms
    public static int longestCommonSubsequence(String text1, String text2) {
        int L1 = text1.length();
        int L2 = text2.length();

        Integer[][] memo = new Integer[L1][L2];

        return countLCS(text1, text2, L1 - 1, L2 - 1, memo);
    }

    public static int countLCS(String s1, String s2, int idx1, int idx2, Integer[][] memo) {
        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }

        if (memo[idx1][idx2] != null) {
            return memo[idx1][idx2];
        }

        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            memo[idx1][idx2] = 1 + countLCS(s1, s2, idx1 - 1, idx2 - 1, memo);
        } else {
            memo[idx1][idx2] = Math.max(
                    countLCS(s1, s2, idx1 - 1, idx2, memo),
                    countLCS(s1, s2, idx1, idx2 - 1, memo));
        }

        return memo[idx1][idx2];
    }

    // DP, iterative
    // Time: O(N^2) 22ms
    public int longestCommonSubsequence0(String text1, String text2) {
        final int L1 = text1.length();
        final int L2 = text2.length();

        int[][] counts = new int[L1][L2];

        // BASE
        counts[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;

        for (int idx = 1; idx < L1; ++idx) {
            counts[idx][0] = text1.charAt(idx) == text2.charAt(0) ? 1 : counts[idx - 1][0];
        }

        for (int idx = 1; idx < L2; ++idx) {
            counts[0][idx] = text1.charAt(0) == text2.charAt(idx) ? 1 : counts[0][idx - 1];
        }

        // TRANSFORM
        for (int i = 1; i < L1; ++i) {
            for (int j = 1; j < L2; ++j) {

                if (text1.charAt(i) == text2.charAt(j)) {
                    counts[i][j] = counts[i - 1][j - 1] + 1;
                } else {
                    counts[i][j] = Math.max(counts[i - 1][j], counts[i][j - 1]);
                }
            }
        }

        return counts[L1 - 1][L2 - 1];
    }

    // DP, iterative
    // Time: O(N^2) 16ms
    public static int longestCommonSubsequence1(String text1, String text2) {
        int L1 = s1.length();
        int L2 = s2.length();

        int[][] counts = new int[L1][L2];

        for (int idx1 = 0; idx1 < L1; ++idx1) {
            for (int idx2 = 0; idx2 < L2; ++idx2) {

                if (s1.charAt(idx1) == s2.charAt(idx2)) {
                    if (idx1 >= 1 && idx2 >= 1) {
                        counts[idx1][idx2] = 1 + counts[idx1 - 1][idx2 - 1];
                    } else {
                        counts[idx1][idx2] = 1;
                    }
                } else {
                    int count1 = idx1 >= 1 ? counts[idx1 - 1][idx2] : 0;
                    int count2 = idx2 >= 1 ? counts[idx1][idx2 - 1] : 0;
                    counts[idx1][idx2] = Math.max(count1, count2);
                }
            }
        }

        return counts[L1 - 1][L2 - 1];
    }

    // DP, iterative, condensed space
    // Time: O(N^2)
    public static int longestCommonSubsequence2(String text1, String text2) {
        int[] length = new int[text2.length() + 1];

        for (int i = 1; i <= text1.length(); ++i) {
            int previous = length[0];

            for (int j = 1; j <= text2.length(); ++j) {
                int temp = length[j];

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    length[j] = 1 + previous;
                } else {
                    length[j] = Math.max(length[j], length[j - 1]);
                }

                previous = temp;
            }
        }

        return length[text2.length()];
    }

}