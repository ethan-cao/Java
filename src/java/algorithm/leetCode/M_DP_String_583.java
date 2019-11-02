package algorithm.leetCode;

/*
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same
where in each step you can delete one character in either string.

The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.

### Example
"sea", "eat" -> 2
one step to make "sea" to "ea" and another step to make "eat" to "ea"

*/

import java.util.ArrayDeque;
import java.util.Deque;

public class M_DP_String_583 {

    public static void main(String... args) {
        System.out.println(minDistance("a", "a"));     // 0
        System.out.println(minDistance("ab", "a"));    // 1
        System.out.println(minDistance("", "aaa"));    // 3
        System.out.println(minDistance("a", "aaa"));   // 2
        System.out.println(minDistance("park", "spake"));   // 3
        System.out.println(minDistance("sea", "eat")); // 2
    }

    // DP:  finding the longest common subsequence.
    // Time: O(N^2)
    public static int minDistance(String word1, String word2) {
        //  dp[i][j] stands for distance of first i chars of word1 and first j chars of word2
        int[][] minDistances = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); ++i) {
            minDistances[i][0] = i;
        }

        for (int i = 0; i <= word2.length(); ++i) {
            minDistances[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); ++i) {
            for (int j = 1; j <= word2.length(); ++j) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    minDistances[i][j] = minDistances[i - 1][j - 1];
                } else {
                    minDistances[i][j] = Math.min(minDistances[i - 1][j], minDistances[i][j - 1]) + 1;
                }
            }
        }

        return minDistances[word1.length()][word2.length()];
    }

    // DP, 1d array
    public static int minDistance1(String word1, String word2) {
        return 1;
    }
}
