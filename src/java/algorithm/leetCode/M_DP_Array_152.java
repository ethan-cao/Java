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
        System.out.println(maxProduct(new int[]{-2, 0, -1}));   // 0
    }

    // 1ms
    // Time: O(N), Space: O(N)
    public static int maxProduct(int[] nums) {
        final int L = nums.length;

        int maxProduct = Integer.MIN_VALUE;
        int localMaxProduct = 1;
        int localMinProduct = 1;

        for (int i = 0; i < L; ++i) {
            int num = nums[i];

            if (num >= 0) {
                localMaxProduct = Math.max(localMaxProduct * num, num);
                localMinProduct = Math.min(localMinProduct * num, num);
            } else {
                int temp = localMaxProduct;
                localMaxProduct = Math.max(localMinProduct * num, num);
                localMinProduct = Math.min(temp * num, num);
            }

            maxProduct = Math.max(maxProduct, localMaxProduct);
        }

        return maxProduct;

    }

    // Prefix suffix array, 1ms
    // Time: O(N), Space: O(N)
    public int maxProduct1(int[] nums) {
        final int L = nums.length;

        int maxProduct = Integer.MIN_VALUE;
        int prefixArrayMaxProduct = 1;
        int suffixArrayMaxProduct = 1;

        for (int i = 0; i < L; ++i) {
            prefixArrayMaxProduct *= nums[i];
            suffixArrayMaxProduct *= nums[L - 1 - i];

            maxProduct = Math.max(maxProduct, Math.max(prefixArrayMaxProduct, suffixArrayMaxProduct));

            // reset to 1 to start over, if product so far is 0
            prefixArrayMaxProduct = prefixArrayMaxProduct == 0 ? 1 : prefixArrayMaxProduct;
            suffixArrayMaxProduct = suffixArrayMaxProduct == 0 ? 1 : suffixArrayMaxProduct;
        }

        return maxProduct;
    }

}
