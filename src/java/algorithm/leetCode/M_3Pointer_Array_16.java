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

public class M_3Pointer_Array_16 {

    public static void main(String... args) {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));  // 2
        System.out.println(threeSumClosest(new int[]{-3, -2, -5, 3, -4}, -1));  // -2
        System.out.println(threeSumClosest(new int[]{1, 1, 1, 0}, 100));  // 3
    }

    // 3Pointer, initial position: left = 0, middle = 1, right = L -1
    public static int threeSumClosest(int[] nums, int target) {
        // !!! cannot use Integer.MAX_VALUE for closestSum,
        // if target is negative, (closestSum - target) overflows
        // just pick a valid initial value
        int closestSum = nums[0] + nums[1] + nums[2];

        Arrays.sort(nums);

        for (int left = 0; left < nums.length - 2; ++left) { // leave room for at least 1 left and 1 middle

            // skip duplicates
            while (left - 1 >= 0 && nums[left] == nums[left-1] && left < nums.length - 2) {
                left++;
            }

            for (int middle = left + 1, right = nums.length - 1; middle < right; ) {
                int sum = nums[left] + nums[middle] + nums[right];

                if (sum == target) {
                    return sum;
                }

                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                if (sum > target) {
                    while (middle < right && right == right - 1) right--;
                    right--;
                } else {
                    while (middle < right && middle == middle + 1) middle++;
                    middle++;
                }
            }
        }

        return closestSum;
    }

}