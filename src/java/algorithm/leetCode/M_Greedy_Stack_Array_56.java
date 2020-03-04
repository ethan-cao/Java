package algorithm.leetCode;

/*
Given a collection of intervals, merge all overlapping intervals

### Example
[[1,3],[2,6],[8,10],[15,18]] --> [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

[[1,4],[4,5]] --> [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

*/

import java.util.*;

public class M_Greedy_Stack_Array_56 {

    public static void main(String... args) {
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {4, 5}})));
    }

    // Time: O(NlogN)
    public static int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }

        List<int[]> mergedIntervals = new ArrayList<>();

        // sort by start
        // impossible to solve by sorting end, as item with higher index and smaller start gets ignored
        // check [[2,3],[4,5],[6,7],[8,9],[1,10]]
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

        // !!! cannot initiate with Integer.MAX or Integer.MIN, since it requires merging interval
        int overlappingGroupStart = intervals[0][0];
        int overlappingGroupEnd = intervals[0][1];

        for (int[] interval : intervals) {
            if (overlappingGroupEnd >= interval[0]) {
                // since sort by start, the previous is always smaller, no need to update overlappingGroupStart
                overlappingGroupEnd = Math.max(overlappingGroupEnd, interval[1]);
            } else {
                mergedIntervals.add(new int[]{overlappingGroupStart, overlappingGroupEnd});

                overlappingGroupStart = interval[0];
                overlappingGroupEnd = interval[1];
            }
        }

        mergedIntervals.add(new int[]{overlappingGroupStart, overlappingGroupEnd});

        return mergedIntervals.toArray(new int[0][0]);
    }

    // Stack
    public int[][] merge1(int[][] intervals) {
        Deque<int[]> mergedIntervals = new ArrayDeque();

        // sort by start
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

        for (int[] interval : intervals) {
            if (mergedIntervals.isEmpty() || interval[0] > mergedIntervals.peek()[1]) {
                mergedIntervals.push(interval);
            } else {
                mergedIntervals.peek()[1] = Math.max(interval[1], mergedIntervals.peek()[1]);
            }
        }

        return mergedIntervals.toArray(new int[0][0]);
    }
}
