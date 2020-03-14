package algorithm.leetCode;

/*
Given an array of non-negative integers, you are initially positioned at index 0
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.

### Example
[2,3,1,1,4] ->  true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

[3,2,1,0,4] ->  false
Explanation: always arrive at index 3. Its maximum jump length is 0, which makes it impossible to reach the last index.
*/

public class M_Greedy_DP_Array_55 {

    public static void main(String... args) {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4})); // T
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4})); // F
        System.out.println(canJump(new int[]{2, 3, 1, 31, 1})); // T
    }

    // Greedy, 1ms
    public static boolean canJump(int[] nums) {
        int currentIdx = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (currentIdx < i) {
                // if the max jump index cannot reach index i, not possible to proceed
                return false;
            }

            currentIdx = Math.max(currentIdx, i + nums[i]); // greedy
        }
        // possible to reach index nums.length -1

        return true;
    }

    // DP, 200ms
    public static boolean canJump1(int[] nums) {
        // if canJumpToEnd[i] is true; we can jump from index i to nums.length-1
        // we need to get canJumpToEnd[0];
        boolean[] canJump = new boolean[nums.length];
        canJump[nums.length - 1] = true;

        for (int idx = nums.length - 2; idx >= 0; --idx) {
            for (int step = 0; step <= nums[idx]; ++step) {

                if (idx + step >= nums.length - 1) {
                    canJump[idx] = true;
                } else {
                    canJump[idx] = canJump[idx + step];
                }

                if (canJump[idx]) {
                    break;
                }
            }
        }

        return canJump[0];
    }

}
