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
        /*

        public boolean canJump(int[] nums) {
    int dis = 0;
    for (int i = 0; i <= dis; i++) {
        dis = Math.max(dis, i + nums[i]);
        if (dis >= nums.length-1) {
            return true;
        }
    }
    return false;
}
         */


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

    // Greedy, 1ms
    public boolean canJump222(int[] nums) {
        final int L = nums.length;
        
        int maxPosition = 0;
        int currentPosition = 0;
    
        while (currentPosition <= maxPosition) {
            maxPosition = Math.max(maxPosition, currentPosition + nums[currentPosition]);
            
            if (maxPosition >= L - 1) {
                return true;
            }
            
            currentPosition++;
        }
    
        return false;
    }

    // DP, 200ms
    // Time: O(N^2)
    public static boolean canJump1(int[] nums) {
        final int L = nums.length;
        boolean[] canJumpToEnd = new boolean[L];
        canJumpToEnd[L - 1] = true;

        // check from right to left
        for (int position = L - 2; position >= 0; --position) {
            int maxJumpDistance = nums[position];

            for (int jumpDistance = 0; jumpDistance <= maxJumpDistance; ++jumpDistance) {

                if (position + jumpDistance >= L - 1) {
                    canJumpToEnd[position] = true;
                } else {
                    // since we check from right to left, canJumpToEnd[position + jumpDistance] is known
                    canJumpToEnd[position] = canJumpToEnd[position + jumpDistance];
                }

                if (canJumpToEnd[position]) {
                    break;
                }
            }
        }

        return canJumpToEnd[0];
    }

}
