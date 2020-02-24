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

public class M_DP_String_1143 {

    public static void main(String... args) {
        System.out.println(longestCommonSubsequence1("abcde", "ace"));          // 3
        System.out.println(longestCommonSubsequence1("abvwerbwbc", "avrabc"));  // 5
    }

    // Time: O(N^2)
    // DP 2d array
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] length = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i <= text1.length(); ++i) {
            for (int j = 1; j <= text2.length(); ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    length[i][j] = 1 + Math.max(length[i - 1][j], length[i][j - 1]);
                } else {
                    length[i][j] = Math.max(length[i - 1][j], length[i][j - 1]);
                }
            }
        }

        return length[text1.length()][text2.length()];
    }

    // Time: O(N^2)
    // DP 1d array
    public static int longestCommonSubsequence1(String text1, String text2) {
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