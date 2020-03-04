package algorithm.leetCode;

/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
find the minimum number of conference rooms required..

### Example
[[1,2],[2,3]] -> 1
[[1,2],[2,3],[3,4],[1,3]] -> 2
[[7,10],[2,4]] -> 1
[[0, 30],[5, 10],[15, 20]] -> 2

*/

import java.util.Arrays;

public class M_Greedy_Array_253 {

    public static void main(String... args) {
        System.out.println(minMeetingRooms(new int[][]{{1, 2}, {2, 4}}));  // 1
        System.out.println(minMeetingRooms(new int[][]{{7, 10}, {2, 4}}));  // 1
        System.out.println(minMeetingRooms(new int[][]{{0, 30}, {5, 10}})); // 2
    }

    // ms
    public static int minMeetingRooms(int[][] intervals) {
        int roomCount = 0;

        if (intervals == null || intervals.length == 0) {
            return roomCount;
        }

        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; ++i) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        for (int start = 0, end = 0; start < intervals.length; ++start) {
            if (starts[start] < ends[end]) {
                roomCount++;
            } else {
                end++;
            }
        }

        return roomCount;
    }

}