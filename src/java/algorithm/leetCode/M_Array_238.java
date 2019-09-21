package algorithm.leetCode;

/*
Given an array nums of n integers where n > 1,
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Please solve it without division and in O(n).

Could you solve it with constant space complexity?
(The output array does not count as extra space for the purpose of space complexity analysis.)

### Example
[1,2,3,4] --> [24,12,8,6]

*/

import java.util.Arrays;

public class M_Array_238 {

    public static void main(String... args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));

        int[] nums1 = {0, 0};   // {0, 0}
        System.out.println(Arrays.toString(productExceptSelf(nums1)));
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] products = new int[nums.length];
        Arrays.fill(products, 1);
        // products = [1, 1, 1, 1];

        for (int i = 1; i < nums.length; ++i) {
            products[i] = products[i - 1] * nums[i - 1];
        }
        // products = [1, nums[1], nums[1] * nums[2], nums[1] * nums[2]  * nums[3] ]

        int right = 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            products[i] *= right;
            right *= nums[i];
        }

        return products;
    }
}
