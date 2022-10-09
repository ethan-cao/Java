package algorithm.leetCode;

/*
Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points.
After, you must delete every element equal to nums[i] - 1 or nums[i] + 1
start with 0 points. Return the maximum number of points you can earn by applying such operations.

1 <= nums.length <= 2 * 10^4
1 <= nums[i] <= 10^4

### Example
[3, 4, 2] -> 6
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.

[2, 2, 3, 3, 3, 4] -> 9
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.

*/

import java.util.Arrays;

public class M_DP_Array_740 {

    public static void main(String... args) {
        System.out.println(deleteAndEarn_1(new int[]{3, 4, 2}));               // 6
        System.out.println(deleteAndEarn_1(new int[]{1, 3, 4, 2}));            // 6
        System.out.println(deleteAndEarn_1(new int[]{2, 2, 3, 3, 3, 4}));      // 9
        System.out.println(deleteAndEarn_1(new int[]{4, 10, 10, 8, 1, 4, 10, 9, 7, 6}));   // 53
    }

    // DP
    // think in terms of the values, since we need to delete adjacent values
    // Time: O(N) 3ms
    public static int deleteAndEarn_1(int[] nums) {
        // 1 <= nums[i] <= 10^4, 10000 possible values
        final int L = 10000;
        int[] points = new int[L + 1];

        // points[i]: points when take the value (i + 1)
        // for duplicate values, if you earn one of them, you earn all of them, so count sum for each value
        for (int num : nums) {
            points[num] += num;
        }

        // BASE CASE
        int[] maxPoints = new int[L + 1];
        maxPoints[1] = points[1];
        maxPoints[2] = Math.max(points[1], points[2]);

        // TRANSFORM
        // take(i) = points[i] + maxPoints[i-2]
        // skip(i) = maxPoints[i-1]
        // maxPoints(i) = max( take(i), skip(i) )
        for (int i = 3; i <= L; ++i) {
            int take = points[i] + maxPoints[i - 2];
            int skip = maxPoints[i - 1];

            maxPoints[i] = Math.max(take, skip);
        }

        return maxPoints[L];
    }

    // DP, condensed space
    // Time: O(N) 2ms
    public static int deleteAndEarn_2(int[] nums) {
        final int L = 10000;
        int[] points = new int[L];

        for (int num : nums) {
            points[num - 1] += num;   // !!! num could be 10000
        }

        //  base case, only consider the 1st num
        int take = points[0];
        int skip = 0;

        // !!! iterate points[]
        // if take i, then drop the i - 1, there is no 2 adjacent values
        for (int i = 1; i < L; ++i) {
            int point = points[i];

            int currentTake = point + skip;
            int currentSkip = Math.max(take, skip);

            take = currentTake;
            skip = currentSkip;
        }

        return Math.max(take, skip);
    }

    // recursive, not really DP
    // 3ms
    public static int deleteAndEarn_3(int[] nums) {
        Arrays.sort(nums);

        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);

        return getMaxPoints(nums, 0, memo);
    }

    private static int getMaxPoints(int[] nums, int idx, int[] memo) {
        if (idx >= nums.length) {
            return 0;
        }

        if (memo[idx] != -1) {
            return memo[idx];
        }

        int take = nums[idx];
        int nextIdx = idx + 1;

        // take all the same value
        while (nextIdx < nums.length && nums[nextIdx] == nums[idx]) {
            take += nums[nextIdx];
            nextIdx++;
        }

        while (nextIdx < nums.length && nums[idx] + 1 == nums[nextIdx]) {
            nextIdx++;
        }

        take += getMaxPoints(nums, nextIdx, memo);

        int skip = getMaxPoints(nums, idx + 1, memo);

        memo[idx] = Math.max(take, skip);

        return memo[idx];
    }

}