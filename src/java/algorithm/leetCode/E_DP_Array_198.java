package algorithm.leetCode;

/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint stopping you
from robbing each of them is that adjacent houses have security system connected and
it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.

### Example
[1,2,3,1] -> 4
Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

[2,7,9,3,1] -> 12
Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

 */

import java.util.Arrays;

public class E_DP_Array_198 {

    public static void main(String[] args) {
        System.out.println(rob0(new int[]{1, 3, 20, 5, 10, 10, 6})); // 37
    }

    /*
    max value amount elements that are not adjacent
    max(n) : max value get rob from values[0] to values[n]

    if rob values[n]      : max(n) = values[n] + max(n-2)
    if not rob values[n]  : max(n) = max(n-1)

    max(0) = values[0]
    max(1) = Math.max(values[0], values[1])
    */

    // DP, iterative, 0ms
    public static int rob0(int[] nums) {
        final int L = nums.length;

        if (L == 1) {
            return nums[0];
        }

        // BASE CASE
        // maxValue(1) = max( take(1), skip(i) )
        // take(1) = nums[0]
        // skip(1) = 0
        // maxValue(2) = max( take(2), skip(2) )
        // take(2) = nums[1]
        // skip(1) = nums[0]
        int[] maxValue = new int[L];

        maxValue[0] = nums[0];
        maxValue[1] = Math.max(nums[0], nums[1]);

        // TRANSFORM
        // maxValue(i) = max( take(i), skip(i) )
        // take(i) = nums[i] + maxValue(i-2)
        // skip(i) = maxValue(i-1)
        for (int i = 2; i < L; ++i) {
            int take = nums[i] + maxValue[i - 2];
            int skip = maxValue[i - 1];

            maxValue[i] = Math.max(take, skip);
        }

        return maxValue[L - 1];
    }

    // DP, iterative, condensed space, 0ms
    public int rob1(int[] values) {
        final int L = values.length;

        if (L == 0) {
            return 0;
        }

        if (L == 1) {
            return values[0];
        }

        int previousMaxValue = values[0];
        int currentMaxValue = Math.max(values[0], values[1]);

        for (int i = 2; i < L; ++i) {
            int valueIfNotRobbing = currentMaxValue;
            int valueIfRobbing = values[i] + previousMaxValue;

            previousMaxValue = currentMaxValue;
            currentMaxValue = Math.max(valueIfRobbing, valueIfNotRobbing);
        }

        return Math.max(currentMaxValue, previousMaxValue);
    }

    // DP, recursive, TLE
    public static int rob2(int[] values) {
        return max(values, values.length - 1);
    }

    private static int max(int[] values, int n) {
        if (n == 0) {
            return values[0];
        }

        if (n == 1) {
            return Math.max(values[0], values[1]);
        }

        int maxIfRob = values[n] + max(values, n - 2);
        int maxIfNotRob = max(values, n - 1);

        return Math.max(maxIfRob, maxIfNotRob);
    }

    // DP, recursive, memo, 0ms
    // Time: O(n), Space: O(n)
    public int rob3(int[] values) {
        final int L = values.length;

        int[] memo = new int[L];
        Arrays.fill(memo, -1);

        return robHouse(values, L - 1, memo);
    }

    private int robHouse(int[] values, int i, int[] memo) {
        if (i < 0) {
            return 0;
        }

        if (i == 0) {
            if (memo[0] == -1) {
                memo[0] = values[0];
            }

            return memo[0];
        }

        if (i == 1) {
            if (memo[1] == -1) {
                memo[1] = Math.max(values[0], values[1]);
            }

            return memo[1];
        }

        if (memo[i] == -1) {
            int valueIfRobbing = robHouse(values, i - 2, memo) + values[i];
            int valueIfNotRobbing = robHouse(values, i - 1, memo);

            memo[i] = Math.max(valueIfRobbing, valueIfNotRobbing);
        }

        return memo[i];
    }

}
