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
        System.out.println(maxProduct(new int[] { 2, 3, -2, 4 })); // 6
        System.out.println(maxProduct(new int[] { -2, 0, -1 })); // 0
    }

    // Kadane's algorithm, 1ms
    // Time: O(N), Space: O(N)
    public static int maxProduct(int[] nums) {
        final int L = nums.length;

        int maxProduct = nums[0];

        int localMaxProduct = nums[0];
        int localMinProduct = nums[0];

        for (int i = 1; i < L; ++i) {
            int num = nums[i];

            if (num >= 0) {
                // compare appendingArrayProduct and newArrayProduct
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

    // 0ms, see Notion for explanation, hard to explain in interview
    // Time: O(N), Space: O(N)
    public int maxProduct1(int[] nums) {
        final int L = nums.length;

        int maxProduct = Integer.MIN_VALUE;
        int product = 1;

        for (int i = 0; i < L; ++i) {
            product *= nums[i];
            maxProduct = Math.max(maxProduct, product);

            product = product == 0 ? 1 : product;
        }

        // this can be skipped
        // product = 1;
        // from right to left
        // for (int i = 0; i < L - 1; ++i) {
        // product *= nums[i];
        // maxProduct = Math.max(maxProduct, product);
        //
        // product = product == 0 ? 1 : product;
        // }

        product = 1;
        // !!! from right to left
        for (int i = L - 1; i >= 1; --i) {
            product *= nums[i];
            maxProduct = Math.max(maxProduct, product);

            product = product == 0 ? 1 : product;
        }

        return maxProduct;
    }

    // 1ms, simplified version of maxProduct1
    // Time: O(N), Space: O(N)
    public int maxProduct2(int[] nums) {
        final int L = nums.length;

        int maxProduct = Integer.MIN_VALUE;
        int prefixMaxProduct = 1;
        int suffixMaxProduct = 1;

        for (int i = 0; i < L - 1; ++i) {
            prefixMaxProduct *= nums[i];
            suffixMaxProduct *= nums[L - 1 - i];

            maxProduct = Math.max(maxProduct, Math.max(prefixMaxProduct, suffixMaxProduct));

            // reset to 1 to start over, if product so far is 0
            prefixMaxProduct = prefixMaxProduct == 0 ? 1 : prefixMaxProduct;
            suffixMaxProduct = suffixMaxProduct == 0 ? 1 : suffixMaxProduct;
        }

        return maxProduct;
    }

}
