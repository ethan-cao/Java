package algorithm.leetCode;

/*
Given a collection of intervals,
find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

### Example
[[1,2],[2,3],[3,4],[1,3]] -> 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

[[1,2],[1,2],[1,2]] -> 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.

[[1,2],[2,3]] -> 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

*/

import java.util.*;

public class M_Greedy_Array_435 {

    public static void main(String... args) {
        System.out.println(eraseOverlapIntervals1(new int[][]{{1, 2}, {2, 3}}));                  // 0
        System.out.println(eraseOverlapIntervals1(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));  // 1
        System.out.println(eraseOverlapIntervals1(new int[][]{{1, 2}, {1, 2}, {1, 2}}));          // 2
    }

    // Time: O(NlogN), Space: O(1)
    // 1ms ??? not sure why this is faster
    public static int eraseOverlapIntervals1(int[][] intervals) {
        if (intervals.length < 2) {
            return 0;
        }

        int minOverlappingIntervalCount = 0;
        int previousNonOverlappingIntervalEnd = Integer.MIN_VALUE;

        // O(NlogN)
        Arrays.sort(intervals, (interval1, interval2) -> interval1[0] - interval2[0]); // sort by start


        // O(N)
        for (int[] interval : intervals) {

            // similar to 452, but count in different condition

            if (previousNonOverlappingIntervalEnd > interval[0]) {
                minOverlappingIntervalCount++;

                // when 2 intervals overlap, the one with larger end should be removed, since it could overlap with others
                previousNonOverlappingIntervalEnd = Math.min(previousNonOverlappingIntervalEnd, interval[1]);

                // This is not necessary if we sort by the end
                // when 2 interval overlap and sort by the end, the previous end is always gonna be smaller than the next end
            } else {
                previousNonOverlappingIntervalEnd = interval[1];
            }
        }

        return minOverlappingIntervalCount;
    }

    // Time: O(NlogN), Space: O(1)
    // 4-5 ms
    public static int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length < 2) {
            return 0;
        }

        int minOverlappingIntervalCount = 0;

        // sort by end
        Arrays.sort(intervals, (interval1, interval2) -> interval1[1] - interval2[1]);

        int previousOverlappingEnd = Integer.MIN_VALUE;

        for (int[] interval : intervals) {
            if (interval[0] < previousOverlappingEnd) {
                minOverlappingIntervalCount++;
            } else {
                previousOverlappingEnd = interval[1];
            }
        }

        return minOverlappingIntervalCount;
    }

}