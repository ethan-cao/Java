package algorithm.leetCode;

/*
Given a collection of intervals, merge all overlapping intervals


### Example
[[1,3],[2,6],[8,10],[15,18]] --> [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

[[1,4],[4,5]] --> [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_Array_48 {

    public static void main(String... args) {
//        int[][] intervals = {
//                {1, 3},
//                {2, 6},
//                {8, 10},
//                {15, 18}
//        };

        int[][] intervals = {
                {1, 4},
                {5, 6},
                {2, 7},
        };


        int[][] mergedIntervals = merge(intervals);

        for (int[] merge : mergedIntervals) {
            System.out.println(Arrays.toString(merge));
        }
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null) {
            return null;
        }

        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> mergedIntervals = new ArrayList();

        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int[] interval : intervals) {
            if (end >= interval[0]) {
                end = Math.max(end, interval[1]);
            } else {
                mergedIntervals.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            }

        }
        mergedIntervals.add(new int[]{start, end});

        return mergedIntervals.toArray(new int[0][]);
    }
}
