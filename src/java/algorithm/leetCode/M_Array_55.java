package algorithm.leetCode;

/*
Given an array of non-negative integers,
you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.

### Example
[2,3,1,1,4] ->  true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

[3,2,1,0,4] ->  false
Explanation: always arrive at index 3. Its maximum jump length is 0, which makes it impossible to reach the last index.
*/


public class M_Array_55 {

    public static void main(String... args) {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4})); // T
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4})); // F
    }

    public static boolean canJump(int[] nums) {
        boolean canJump = false;

        return canJump;
    }

}
