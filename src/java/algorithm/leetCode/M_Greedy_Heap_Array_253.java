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

import java.util.*;

public class M_Greedy_Heap_Array_253 {

    public static void main(String... args) {
        long before = System.currentTimeMillis();
        minMeetingRooms1(new int[][]{{0, 30}, {5, 10}, {15, 20}});
        long after = System.currentTimeMillis();
        System.out.println("time : " + (after - before) + " ms");

        System.out.println(minMeetingRooms(new int[][]{{1, 2}, {2, 4}}));  // 1
        System.out.println(minMeetingRooms(new int[][]{{7, 10}, {2, 4}}));  // 1
        System.out.println(minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}})); // 2
    }

    // 1ms, Greedy
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

        int startIdx = 0;
        int endIdx = 0;

        while (startIdx < starts.length) {
            if (starts[startIdx] < ends[endIdx]) {
                roomCount++;
            } else {
                endIdx++;
            }

            startIdx++;
        }

        return roomCount;
    }

    // 50 ms, Heap
    public static int minMeetingRooms1(int[][] intervals) {
        Arrays.sort(intervals, (interval1, interval2) -> interval1[0] - interval2[0]);

        //Add the current Ongoing Meeting
        PriorityQueue<Integer> meetings = new PriorityQueue<>(intervals.length);

        for (int[] interval : intervals) {
            int startTime = interval[0];
            int endTime = interval[1];

            if (!meetings.isEmpty() && startTime >= meetings.peek()) {
                meetings.poll();
            }

            meetings.offer(endTime);
        }

        return meetings.size();
    }

}