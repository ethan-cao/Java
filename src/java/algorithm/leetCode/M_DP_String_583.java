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

public class M_DP_String_583 {

    public static void main(String... args) {
        System.out.println(minDistance1("a", "a")); // 0
        System.out.println(minDistance1("ab", "a")); // 1
        System.out.println(minDistance1("", "aaa")); // 3
        System.out.println(minDistance1("a", "aaa")); // 2
        System.out.println(minDistance1("park", "spake")); // 3
        System.out.println(minDistance1("sea", "eat")); // 2
    }

    // DP, same idea as finding the longest common subsequence.
    // Time: O(N^2) 17ms
    public static int minDistance(String word1, String word2) {
        int L1 = word1.length();
        int L2 = word2.length();

        // minDistances[i][j]: distance of first i chars of word1 and first j chars of word2
        // !!! need to use L1+1, L2+1 as limit
        // the base case is one of the word is empty
        int[][] minDistances = new int[L1 + 1][L2 + 1];

        for (int i = 0; i <= L1; ++i) {
            minDistances[i][0] = i;
        }

        for (int i = 0; i <= L2; ++i) {
            minDistances[0][i] = i;
        }

        for (int i = 1; i <= L1; ++i) {
            for (int j = 1; j <= L2; ++j) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    minDistances[i][j] = minDistances[i - 1][j - 1];
                } else {
                    minDistances[i][j] = 1 + Math.min(minDistances[i - 1][j], minDistances[i][j - 1]);
                }
            }
        }

        return minDistances[L1][L2];
    }

    // DP, condensed space
    public static int minDistance1(String word1, String word2) {
        int[] minDistances = new int[word2.length() + 1];

        for (int i = 0; i <= word2.length(); ++i) {
            minDistances[i] = i;
        }

        for (int i = 1; i <= word1.length(); ++i) {
            int previous = minDistances[0];
            minDistances[0] = i;

            for (int j = 1; j <= word2.length(); ++j) {
                int temp = minDistances[j];

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    minDistances[j] = previous;
                } else {
                    minDistances[j] = 1 + Math.min(minDistances[j], minDistances[j - 1]);
                }

                previous = temp;
            }
        }

        return minDistances[word2.length()];
    }
}
