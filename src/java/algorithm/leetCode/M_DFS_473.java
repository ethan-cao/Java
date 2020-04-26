package algorithm.leetCode;

/*
Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has,
please find out a way you can make one square by using up all those matchsticks.
You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

input will be several matchsticks the girl has, represented with their stick length.
output will be true / false, to represent if you could make one square using all matchsticks the little match girl has

The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.

### Example
[1,1,2,2,2] -> true
You can form a square with length 2, one side of the square came two sticks with length 1.

[3,3,3,3,4] -> false
You cannot find a way to form a square with all the matchsticks.
*/

import java.util.Arrays;

public class M_DFS_473 {

    public static void main(String[] args) {
        System.out.println(makesquare(new int[]{1, 1, 2, 2, 2})); // true
        System.out.println(makesquare(new int[]{3, 3, 3, 3, 4})); // false
    }

    // DFS, ms
    public static boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) {
            return false;
        }

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 4 != 0) {
            return false;
        }

        // pick the maximum element that is not used
        // trying a longer matchstick first will get to negative conclusion earlier
        Arrays.sort(nums);

        boolean[] used = new boolean[nums.length];

        return canMakeSquare(nums, nums.length - 1, 0, sum / 4, 0, used);
    }

    // side: there are 4 side of a square to be filled
    private static boolean canMakeSquare(int[] nums, int idx, int sum, int targetSum, int side, boolean[] used) {
        // if there is only 1 side left, since all the rest reaches target, the last one reaches targetSum for sure
        if (side == 3) {
            return true;
        }

        if (sum == targetSum) {
            return canMakeSquare(nums, nums.length - 1, 0, targetSum, side + 1, used);
        } else if (sum > targetSum) {
            return false;
        } else {
            for (int i = idx; i >= 0; --i) {
                if (used[i]) {
                    continue;
                }

                used[i] = true;

                if (canMakeSquare(nums, i - 1, sum + nums[i], targetSum, side, used)) {
                    return true;
                }

                used[i] = false;

                // dedup
                while (i > 0 && nums[i] == nums[i - 1]) {
                    i--;
                }
            }

            return false;
        }
    }

}