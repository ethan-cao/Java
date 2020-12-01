package algorithm.leetCode;

/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
The intersection of two closed intervals is a set of real numbers that is either empty,
or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9

### Example
Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
see the picture https://leetcode.com/problems/interval-list-intersections/

*/

import java.util.*;

public class M_2Pointer_Array_986 {

    // same idea as algorithm.sorting.MergeSort.TopDown.merge
    // Time: O(M + N), where M, N are the lengths of A and B respectively.
    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> intersections = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < A.length && j < B.length) {
            int start = Math.max(A[i][0], B[j][0]);
            int end = Math.min(A[i][1], B[j][1]);

            // !!! include =, since the single value could also be an intersection
            if (start <= end) {
                intersections.add(new int[]{start, end});
            }

            if (A[i][1] <= B[j][1]) {
                // not possible for A[i] to have intersection with others, pass on
                i++;
            } else {
                j++;
            }
        }

        // List<T> -> T[]
        return intersections.toArray(new int[intersections.size()][2]);
    }

}