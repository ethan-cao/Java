package algorithm.leetCode;

/*
You are given an array of n pairs where pairs[i] = [left_i, right_i] and left_i < right_i.

A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.

Return the length longest chain which can be formed.

You do not need to use up all the given intervals. You can select pairs in any order.

n == pairs.length
1 <= n <= 1000
-1000 <= left_i < right_i <= 1000

### Example
pairs = [[1,2],[2,3],[3,4]] -> 2
Explanation: The longest chain is [1,2] -> [3,4].

pairs = [[1,2],[7,8],[4,5]] -> 3
Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].

*/

import java.util.Arrays;

public class M_DP_646 {

    public static void main(String[] args) {
    }

    // DP, 36ms
    public int findLongestChain1(int[][] pairs) {
        int[][] sortedPairs = Arrays.stream(pairs)
                .sorted((p1, p2) -> Integer.compare(p1[1], p2[1]))
                .toArray(int[][]::new);

        int L = sortedPairs.length;

        // counts[i]: longest chain ending at i
        int[] counts = new int[L];

        // BASE
        Arrays.fill(counts, 1);

        // TRANSFORM
        for (int end = 0; end < L; ++end) {
            for (int start = 0; start < end; ++start) {

                int count = sortedPairs[end][0] > sortedPairs[start][1] ? counts[start] + 1 : counts[start];
                counts[end] = Math.max(counts[end], count);
            }
        }

        return counts[L - 1];
    }

    // Greedy, 26ms
    public static int findLongestChain2(int[][] pairs) {
        int[][] sortedPairs = Arrays.stream(pairs)
                .sorted((p1, p2) -> Integer.compare(p1[1], p2[1]))
                .toArray(int[][]::new);

        int L = sortedPairs.length;
        int longestLength = 1;

        int preEnd = sortedPairs[0][1];

        for (int i = 1; i < L; ++i) {
            int curStart = sortedPairs[i][0];

            if (preEnd < curStart) {
                longestLength++;
                preEnd = sortedPairs[i][1];
            }
        }

        return longestLength;
    }


}

