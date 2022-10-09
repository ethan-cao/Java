package algorithm.leetCode;

/*
Given a char array representing tasks CPU need to do.
It contains capital letters A to Z where different letters represent different tasks.
Tasks could be done without original order. Each task could be done in one interval.
For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks,
there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

The number of tasks is in the range [1, 10000], The integer n is in the range [0, 100].

### Example
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

*/

import java.util.*;

public class M_Greedy_Array_621 {

    public static void main(String... args) {
        System.out.println(leastInterval(new char[]{'A', 'A', 'A'}, 2));  // 7
//        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));  // 8
//        System.out.println(leastInterval(new char[]{'A', 'A', 'B', 'B', 'C', 'C', 'D'}, 5));  // 9
//        System.out.println(leastInterval(new char[]{'A', 'A', 'B', 'B', 'B', 'B', 'C', 'D', 'D', 'D'}, 3));  // 9
    }

    // Time: O(N),
    //  if there is idle interval, leastIntervalCount = idleCount + tasksCount;
    //  if there is no idle interval, leastIntervalCount = tasksCount;
    public static int leastInterval(char[] tasks, int n) {
        // sort the count since we need to arrange task with most occurrence first (Greedy)
        // the most frequent tasks get scheduled first, since they need the most idles than any other tasks

        // This is like partitioning tasks, in each partition, the same type of task can occur only once
        // pick the most frequent task, the count is the partition count

        int[] taskCounter = new int[26];
        for (char task : tasks) {
            taskCounter[task - 'A']++;
        }
        Arrays.sort(taskCounter);

        int mostFrequentTaskFrequency = taskCounter[25];
        int mostFrequentTaskCount = 0;

        for (int i = 25; i >= 0; --i) {
            if (taskCounter[i] == mostFrequentTaskFrequency) {
                mostFrequentTaskCount++;
            }
        }

        int intervalBetweenMostFrequentTaskCount = (mostFrequentTaskFrequency - 1) * (n - (mostFrequentTaskCount - 1));

        // task count that excludes the most frequent task
        int NonMostFrequentTaskCount = tasks.length - mostFrequentTaskCount * mostFrequentTaskFrequency;

        int idleIntervalCount = Math.max(0, intervalBetweenMostFrequentTaskCount - NonMostFrequentTaskCount);

        return tasks.length + idleIntervalCount;
    }

    // PriorityQueue
    // https://leetcode.com/problems/task-scheduler/discuss/104501/Java-PriorityQueue-solution-Similar-problem-Rearrange-string-K-distance-apart
    public static int leastInterval1(char[] tasks, int n) {
        return 1;
    }
}