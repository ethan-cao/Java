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
        if (nums == null || nums.length <= 3) {
            return false;
        }

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 4 != 0) {
            return false;
        }

        Arrays.sort(nums);

        return visit(nums, 0, nums.length - 1, 0, sum / 4, new boolean[nums.length]);
    }

    private static boolean visit(int[] nums, int part, int idx, int curr, int target, boolean[] used) {
        if (part == 3) {
            return true;
        }

        if (curr > target) {
            return false;
        } else if (curr == target) {
            return visit(nums, part + 1, nums.length - 1, 0, target, used);
        }

        for (int i = idx; i >= 0; i--) {
            if (!used[i] && curr + nums[i] <= target) {
                used[i] = true;
                if (visit(nums, part, i - 1, curr + nums[i], target, used)) return true;
                used[i] = false;

                while (i > 0 && nums[i] == nums[i - 1]) i--;
                if (curr == 0) return false;
                if (curr + nums[i] == target) return false;
            }
        }

        return false;
    }

}