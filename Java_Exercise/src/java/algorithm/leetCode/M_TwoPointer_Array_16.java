package algorithm.leetCode;

/*
Given an array nums of n integers and an integer target,
find 3 integers in nums such that the sum is closest to target. Return the sum of the three integers.

You may assume that each input would have exactly one solution.
nums.length >= 3

### Example
nums = [-1, 2, 1, -4], and target = 1.
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

*/

import java.util.Arrays;

public class M_TwoPointer_Array_16 {

    public static void main(String... args) {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));  // 2
        System.out.println(threeSumClosest(new int[]{-3, -2, -5, 3, -4}, -1));  // -2
        System.out.println(threeSumClosest(new int[]{1, 1, 1, 0}, 100));  // 3
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        // cannot use Integer.MAX_VALUE for closestSum !!!
        // if target is negative, (closestSum - target) overflows
        // just pick a random initial value
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int start = 0; start < nums.length - 2; ++start) { // !!! < nums.length - 2

            // optimization
            if (start > 0 && nums[start] == nums[start - 1]) {
                continue;
            }

            for (int middle = start + 1, end = nums.length - 1; middle < end; ) {
                int sum = nums[start] + nums[middle] + nums[end];

                if (sum == target) {
                    return sum;
                }

                closestSum = Math.abs(sum - target) < Math.abs(closestSum - target) ? sum : closestSum;

                if (sum > target) {
                    end--;
                } else {
                    middle++;
                }

            }
        }

        return closestSum;
    }

}