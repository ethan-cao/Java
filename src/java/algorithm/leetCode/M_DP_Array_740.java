package algorithm.leetCode;

/*
Given an array nums of integers, you can perform operations on the array.
In each operation, you pick any nums[i] and delete it to earn nums[i] points.
After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

start with 0 points. Return the maximum number of points you can earn by applying such operations.

The length of nums is at most 20000.
Each element nums[i] is an integer in the range [1, 10000].

### Example
nums = [3, 4, 2] -> 6
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.

[2, 2, 3, 3, 3, 4] -> 9
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.

### Review:

*/

public class M_DP_Array_740 {

    public static void main(String... args) {
        System.out.println(deleteAndEarn(new int[]{3, 4, 2}));               // 6
        System.out.println(deleteAndEarn(new int[]{1, 3, 4, 2}));            // 6
        System.out.println(deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));      // 9
        System.out.println(deleteAndEarn(new int[]{4, 10, 10, 8, 1, 4, 10, 9, 7, 6}));   // 53
    }

    // DP
    public static int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // there are 10000 possible value
        final int SIZE = 10001;

        // for duplicate values, if you earn one of them, you end up earning all of them
        int[] sums = new int[SIZE];
        for (int num : nums) {
            sums[num] += num;
        }

        // maxPoints[i]: max points for {0 ... i}
        // we need max points for {0 ... 10000}
        int[] maxPoints = new int[SIZE];
        maxPoints[0] = sums[0];
        maxPoints[1] = sums[1];

        for (int i = 2; i < maxPoints.length; ++i) {
            // delete i, dismiss i-1   : sums[i] + maxPoints[i-2]
            // delete i - 1, dismiss i : maxPoints[i-1]
            maxPoints[i] = Math.max(sums[i] + maxPoints[i - 2], maxPoints[i - 1]);
        }

        return maxPoints[maxPoints.length - 1];
    }

    // DP, without extra space
    public static int deleteAndEarn1(int[] nums) {
        final int SIZE = 10000;

        int[] sums = new int[SIZE];
        for (int num : nums) {
            sums[num - 1] += num;
        }

        return 1;
    }


}
