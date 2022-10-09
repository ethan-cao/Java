package algorithm.leetCode;

/*
There are a number of spherical balloons spread in two-dimensional space.
For each balloon, provided input is the  start  and  end  coordinates of the horizontal diameter.
Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice.

Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis.
A balloon with x(start) and x(end) bursts by an arrow shot at x if x(start) ≤ x ≤ x(end)
There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely.
The problem is to find the minimum number of arrows that must be shot to burst all balloons.

### Example
[[1,2],[2,3]] -> 1
[[1,2],[2,3],[3,4],[1,3]] -> 2

[[10,16], [2,8], [1,6], [7,12]] -> 2
Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6])
and another arrow at x = 11 (bursting the other two balloons).

*/

import java.util.*;

public class M_Greedy_Array_452 {

    public static void main(String... args) {
        System.out.println(findMinArrowShots1(new int[][]{{1, 2}, {2, 3}}));                                             // 1
        System.out.println(findMinArrowShots1(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}}));                          // 2
        System.out.println(findMinArrowShots1(new int[][]{{1, 2}, {3, 8}, {8, 9}, {10, 16}, {2, 8}, {1, 6}, {7, 12}}));  // 3
    }

    // Time: O(OlogN), Space: O(1)
    // sort start or end are pretty much the same performance
    public static int findMinArrowShots1(int[][] points) {
        if (points.length < 2) {
            return points.length;
        }

        // sort points by start
        Arrays.sort(points, (p1, p2) -> p1[0] - p2[0]);

        int overlappingGroupEnd = Integer.MIN_VALUE;
        int minArrowCount = 0;

        // if overlappingGroupEnd is points[0][1], minArrowCount's initial value is 1

        for (int[] point : points) {
            if (overlappingGroupEnd >= point[0]) {
                // !!!! when still in the same overlapping group, pick the smaller end
                // since it leads to more chance of overlapping
                // and minArrowCount is actually maxOverlappingBalloonGroupCount
                overlappingGroupEnd = Math.min(overlappingGroupEnd, point[1]);
            } else {
                minArrowCount++;
                overlappingGroupEnd = point[1];  // update group end when entering a new group
            }
        }

        return minArrowCount;
    }

    // Time: O(OlogN), Space: O(1)
    public static int findMinArrowShots(int[][] points) {
        if (points.length < 2) {
            return points.length;
        }

        int minArrowCount = 0;

        // the more balloons overlap, the less arrow we need
        // so minArrowCount is maxOverlappingBalloonGroupCount

        // sort points by end
        Arrays.sort(points, (p1, p2) -> p1[1] - p2[1]);

        int overlappingGroupEnd = Integer.MIN_VALUE;

        for (int[] point : points) {

            // increase maxOverlappingBalloonGroupCount if the next one does not overlap with the previous one
            // namely, when reach the end of the overlapping group
            if (overlappingGroupEnd < point[0]) {
                // corner case: when 2 balloons share the border, it is considered they overlap
                minArrowCount++;
                overlappingGroupEnd = point[1]; // update group end when entering a new group
            }
        }

        return minArrowCount;
    }


}