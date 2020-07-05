package algorithm.leetCode;

/*
Given an integer array nums,
find the contiguous subarray within an array (containing at least one number) which has the largest product.

### Example
[2,3,-2,4] -> 6
Explanation: [2,3] has the largest product 6

[-2,0,-1] -> 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
*/

public class M_DP_Array_152 {

    public static void main(String... args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4})); // 6
        System.out.println(maxProduct(new int[]{-2, 0, -1})); // 0
    }

    // 1ms, O(1) space, O(n) time
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int result = nums[0];
        int maxProduct = nums[0];
        int minProduct = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            int num = nums[i];

            if (num >= 0) {
                maxProduct = Math.max(maxProduct * num, num);
                minProduct = Math.min(minProduct * num, num);
            } else {
                int temp = maxProduct;
                maxProduct = Math.max(minProduct * num, num);
                minProduct = Math.min(temp * num, num);
            }

            result = Math.max(result, maxProduct);
        }

        return result;
    }

}
