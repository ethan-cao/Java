package algorithm.leetCode;

/*
A conveyor belt has packages that must be shipped from one port to another within D days.
The i-th package on the conveyor belt has a weight of weights[i].
Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
We may not load more weight than the maximum weight capacity of the ship.
Return the least weight capacity of the ship
that will result in all the packages on the conveyor belt being shipped within D days.

1 <= D <= weights.length <= 50000
1 <= weights[i] <= 500

### Example
Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
Output: 15
Explanation:
A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Input: weights = [3,2,2,4,1,4], D = 3
Output: 6
Explanation:
A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

Input: weights = [1,2,3,1,1], D = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1
*/

import java.util.Arrays;

public class M_BinarySearch_1011 {

    // Time: O(logN). 9ms
    public int shipWithinDays(int[] weights, int D) {
        int[] boundaries = getBoundaries(weights);
        int left = boundaries[0];
        int right = boundaries[1];

        while (left <= right) {
            int middle = left + (right - left) / 2;
            boolean canShip = canShip(weights, middle, D);

            if (canShip) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return left;
    }

    private boolean canShip(int[] weights, int capacity, int days) {
        int daysRequired = 0;
        int load = 0;

        for (int i = 0; i < weights.length; ++i) {
            int weight = weights[i];

            if (load + weight <= capacity) {
                load += weight;
            } else {
                daysRequired++;
                load = weight;
            }

            if (daysRequired > days) {
                return false;
            }
        }

        if (load > 0) {
            daysRequired++;
        }

        return daysRequired <= days;
    }

    private int[] getBoundaries(int[] weights) {
        int sum = 0;
        int max = 0;

        for (int i = 0; i < weights.length; ++i) {
            sum += weights[i];
            max = Math.max(max, weights[i]);
        }

        return new int[]{max, sum};
    }

    // Time:
    public int shipWithinDays1(int[] weights, int D) {
        int[] boundaries = getBoundaries(weights);
        int left = boundaries[0];
        int right = boundaries[1];

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int daysRequired = getDaysRequired(weights, middle);

            if (daysRequired > D) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }

    private int getDaysRequired(int[] weights, int maxCapacity) {
        int daysRequired = 0;
        int load = 0;

        for (int weight : weights) {
            load += weight;

            if (load > maxCapacity) {
                daysRequired++;
                load = weight;
            }
        }

        return daysRequired + 1;
    }

}
