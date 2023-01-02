package algorithm.leetCode;

/*
You are given an array of n pairs where pairs[i] = [left_i, right_i] and left_i < right_i.
A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
Return the length longest chain which can be formed.
You do not need to use up all the given intervals. You can select pairs in any order.

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

    // DP, 36ms
    // Time: O(N^2)
    public int findLongestChain1(int[][] pairs) {
        int L = pairs.length;

        int[] counts = new int[L];
        Arrays.fill(counts, 1);

        int maxCount = 1;

        // difference between 300 and 646: in 646 you can re-order item, in 300 you
        // cannot
        // so if sort paris, so we only need to look in 1 direction left to right
        // otherwise, we need to look in 2 direction, left to right and right to left

        // mutate input sorting
        // it's also ok to sort based on (p1, p2) -> Integer.compare(p1[0], p2[0])
        Arrays.sort(pairs, (p1, p2) -> Integer.compare(p1[1], p2[1]));

        // without mutating the input
        int[][] sortedPairs = Arrays.stream(pairs)
                .sorted((p1, p2) -> Integer.compare(p1[1], p2[1]))
                .toArray(int[][]::new);

        for (int i = 1; i < L; ++i) {
            for (int j = 0; j < i; ++j) {

                if (pairs[j][1] < pairs[i][0]) {
                    counts[i] = Math.max(counts[i], counts[j] + 1);
                }

                maxCount = Math.max(maxCount, counts[i]);
            }
        }

        return maxCount;
    }

    // Greedy, 26ms
    // Time: O(NlogN)
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
