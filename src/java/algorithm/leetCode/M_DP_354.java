package algorithm.leetCode;

/*
You are given a 2D array of integers envelopes
where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
One envelope can fit into another if and only if both the width and height of one envelope are
greater than the other envelope's width and height.

Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
Note: You cannot rotate an envelope.

1 <= envelopes.length <= 10^5
envelopes[i].length == 2
1 <= wi, hi <= 10^5

### Example
envelopes = [[5,4],[6,4],[6,7],[2,3]] -> 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

envelopes = [[1,1],[1,1],[1,1]] -> 1
*/

import java.util.Arrays;

public class M_DP_354 {

    public static void main(String[] args) {
        int[][] envelopes1 = {{5, 4}, {6, 4}, {6, 7}, {2, 3}}; // 3
        int[][] envelopes2 = {{1, 1}};   // 1
        int[][] envelopes3 = {{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}}; // 4

        System.out.println(maxEnvelopes1(envelopes3));
    }

    // Greedy
    // Time: O(NlogN), 84ms
    public static int maxEnvelopes1(int[][] envelopes) {
        int max = 0;

        int L = envelopes.length;
        int[] count = new int[L];

        // Sorting the width reduces the problem by one dimension
        // If width is strictly increasing, the problem is equivalent to find Longest Increasing Subsequence in height dimension
        // sort, ascend on width and descend on height if width are same.
        // why descend on height?
        // [3, 4] cannot contains [3, 3], need to put [3, 4] before [3, 3] when sorting
        // otherwise it will be counted as an increasing number if the order is [3, 3], [3, 4]
        int[][] sortedEnvelopes = Arrays.stream(envelopes)
                .sorted((e1, e2) -> e1[0] == e2[0] ? Integer.compare(e2[1], e1[1]) : Integer.compare(e1[0], e2[0]))
                .toArray(int[][]::new);

        // alternatively
        // Arrays.sort(envelopes, (a, b) -> {
        //     if (a[0] != b[0]) {
        //         return a[0] - b[0];  // sort by width ascending
        //     }
        //     return b[1] - a[1];      // if widths equal, sort by height descending
        // });


        // e.g., after sorting: (1,3), (3,5), (6,8), (6,7), (8,4), (9,5)
        // transform to question find Longest Increasing Subsequence : [3,5,8,7,4,5]
        for (int[] envelope : sortedEnvelopes) {
            int height = envelope[1];
            int index = Arrays.binarySearch(count, 0, max, height);

            if (index < 0) {
                index = -(index + 1);
            }

            count[index] = envelope[1];

            if (index == max) {
                max++;
            }
        }

        return max;
    }

    // DP
    // Time: O(N^2), LTE
    public static int maxEnvelopes2(int[][] envelopes) {
        int L = envelopes.length;

        int[] counts = new int[L];
        Arrays.fill(counts, 1);

        int maxCount = 1;

        // does not mutate the input
        int[][] sortedEnvelopes = Arrays.stream(envelopes)
                .sorted((e1, e2) -> e1[0] == e2[0] ? Integer.compare(e2[1], e1[1]) : Integer.compare(e1[0], e2[0]))
                .toArray(int[][]::new);

        // alternatively, mutate the input
        // Arrays.sort(envelopes, (e1, e2) -> {
        //     if (e1[0] == e2[0]) {
        //         return Integer.compare(e2[1], e1[1]);
        //     } else {
        //         return Integer.compare(e1[0], e2[0]);
        //     }
        // });

        for (int end = 1; end < L; ++end) {
            for (int start = 0; start < end; ++start) {

                // Since the width is increasing, only need to consider height
                if (sortedEnvelopes[start][1] < sortedEnvelopes[end][1]) {
                    counts[end] = Math.max(counts[end], counts[start] + 1);
                }

                maxCount = Math.max(maxCount, counts[end]);
            }
        }

        return maxCount;
    }

}
