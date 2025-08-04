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
        System.out.println(canJump(new int[] { 2, 3, 1, 1, 4 })); // T
        System.out.println(canJump(new int[] { 3, 2, 1, 0, 4 })); // F
        System.out.println(canJump(new int[] { 2, 3, 1, 31, 1 })); // T
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Greedy, 1ms
    // Time: O(n)
    public static boolean canJump(int[] nums) {
        final int L = nums.length;

        for (int idx = 0, maxIdx = 0; idx < L; ++idx) {
            if (maxIdx < idx) {
                // if the max jump index cannot reach index i, not possible to proceed
                return false;
            }

            maxIdx = Math.max(maxIdx, idx + nums[idx]); // greedy
        }
        // possible to reach index nums.length -1

        return true;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Greedy, 1ms
    public boolean canJump222(int[] nums) {
        final int L = nums.length;

        int idx = 0;
        int maxIdx = 0;

        while (idx < L) {
            int maxJump = nums[idx];
            maxIdx = Math.max(maxIdx, idx + maxJump);

            // reaching the last index
            if (maxIdx >= L - 1) {
                return true;
            }

            idx++;
            // not possible to each idx
            if (idx > maxIdx) {
                return false;
            }
        }

        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, 1200ms
    // Time: O(N^2)
    public boolean canJump2(int[] nums) {
        int L = nums.length;

        boolean[] canJumpTo = new boolean[L];
        canJumpTo[0] = true;

        for (int end = 1; end < L; ++end) {
            for (int start = 0; start < L; ++start) {
                if (canJumpTo[start]) {
                    canJumpTo[end] = start + nums[start] >= end;
                }
            
                if (canJumpTo[end]) {
                    break;
                }
            }
        }

        return canJumpTo[L - 1];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, 200ms
    // Time: O(N^2)
    public static boolean canJump1(int[] nums) {
        final int L = nums.length;

        // jump[i]: if possible to jump from i to the end
        boolean[] jump = new boolean[L];
        jump[L - 1] = true;

        // check from end to start
        for (int startIdx = L - 2; startIdx >= 0; --startIdx) {
            int maxJumpDistance = nums[startIdx];

            for (int jumpDistance = 0; jumpDistance <= maxJumpDistance; ++jumpDistance) {

                if (startIdx + jumpDistance >= L - 1) {
                    jump[startIdx] = true;
                } else {
                    // since we check from end to start, canJumpToEnd[position + jumpDistance] is known
                    jump[startIdx] = jump[startIdx + jumpDistance];
                }

                if (jump[startIdx]) {
                    break;
                }
            }
        }

        return jump[0];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Brute force, LTE
    public boolean canJump11(int[] nums) {
        return check(nums, 0);
    }

    boolean check(int[] nums, int idx) {
        if (idx == nums.length - 1) {
            return true;
        }

        int maxJump = nums[idx];

        for (int jump = 1; jump <= maxJump; ++jump) {
            int nextIdx = idx + jump;

            if (nextIdx < nums.length && check(nums, nextIdx)) {
                return true;
            }
        }

        return false;
    }
}
