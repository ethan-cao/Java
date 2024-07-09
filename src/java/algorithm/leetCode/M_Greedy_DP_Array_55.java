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
    public static boolean canJump(int[] nums) {
        final int L = nums.length;

        for (int currentIdx = 0, nextIdx = 0; currentIdx < L; ++currentIdx) {
            if (nextIdx < currentIdx) {
                // if the max jump index cannot reach index i, not possible to proceed
                return false;
            }

            nextIdx = Math.max(nextIdx, currentIdx + nums[currentIdx]); // greedy
        }
        // possible to reach index nums.length -1

        return true;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Greedy, 1ms
    public boolean canJump222(int[] nums) {
        final int L = nums.length;

        int currentIdx = 0;
        int nextIdx = 0;

        while (nextIdx >= currentIdx) {
            nextIdx = Math.max(nextIdx, currentIdx + nums[currentIdx]);

            if (nextIdx >= L - 1) {
                return true;
            }

            currentIdx++;
        }

        return false;
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
    // DP, 1200ms
    // Time: O(N^2)
    public boolean canJump2(int[] nums) {
        boolean jump[] = new boolean[nums.length];
        jump[0] = true;

        for (int end = 1; end < nums.length; ++end) {
            for (int start = 0; start < end; ++start) {
                if (jump[start]) {
                    jump[end] = nums[start] >= end - start;
                }

                if (jump[end]) {
                    break;
                }
            }
        }

        return jump[nums.length - 1];
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
