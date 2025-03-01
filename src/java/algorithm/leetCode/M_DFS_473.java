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


473 is the same as 698, when k = 4

*/

import java.util.Arrays;

public class M_DFS_473 {

    public static void main(String[] args) {
        System.out.println(makesquare(new int[] { 1, 1, 2, 2, 2 })); // true
        System.out.println(makesquare(new int[] { 3, 3, 3, 3, 4 })); // false
    }

    // pick the maximum element that is not used
    // trying a longer matchstick first will get to negative conclusion earlier
    public static boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int stick : matchsticks) {
            sum += stick;
        }

        if (sum % 4 != 0) {
            return false;
        }

        int squareLength = sum / 4;

        // pick the maximum element that is not used as early as possible
        // trying a longer matchstick first leads to negative conclusion earlier
        Arrays.sort(matchsticks);
        boolean[] isUsed = new boolean[matchsticks.length];

        // !!! iterate backwards, since trying longer stick leads to (sum > targetSum)
        // earlier
        return check(matchsticks, isUsed, matchsticks.length - 1, 0, squareLength, 4);
    }

    private static boolean check(int[] matchsticks, boolean[] isUsed, int startIdx, int sum, int target, int count) {
        // optimization
        // there are 4 side of a square to be filled
        // if there is only 1 side left, since all the rest reaches target, the last one
        // reaches targetSum for sure
        if (count == 1) {
            return true;
        }

        if (sum > target) {
            return false;
        }

        if (sum == target) {
            // !!! iterate backwards, start over again
            return check(matchsticks, isUsed, matchsticks.length - 1, 0, target, count - 1);
        }

        // !!! iterate backwards
        // why not iterate from matchsticks.length - 1? avoid duplicates
        for (int i = startIdx; i >= 0; --i) {
            if (isUsed[i]) {
                continue;
            }

            isUsed[i] = true;

            boolean result = check(matchsticks, isUsed, i - 1, sum + matchsticks[i], target, count);
            if (result) {
                return true;
            }

            // backtrack
            isUsed[i] = false;

            while (i - 1 > 0 && matchsticks[i - 1] == matchsticks[i]) {
                i--;
            }
        }

        return false;
    }

}