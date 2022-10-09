package algorithm.leetCode;

/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

### Example
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3

### Condition
i<=j
You may assume that the array does not change.
There are many calls to sumRange function.

R : 1
Similar : 238
 */

public class E_Array_303 {

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};

        NumArray obj = new NumArray(nums);
        System.out.println(obj.sumRange(0, 2)); // 1
        System.out.println(obj.sumRange(2, 5)); // -1
        System.out.println(obj.sumRange(0, 5)); // -3
    }

    static class NumArray {
        private int[] sum;

        public NumArray(int[] nums) {
            this.sum = new int[nums.length]; // by default, all is 0

            if (nums.length == 0) {
                return;
            }

            this.sum[0] = nums[0];
            for (int i = 1; i < nums.length; ++i) {
                this.sum[i] = this.sum[i - 1] + nums[i];
            }
            // sum[i] = ∑ nums[i]
        }

        public int sumRange(int i, int j) {
            if (i > j || i < 0 || j > this.sum.length - 1) {
                return 0;
            }

            return 0 == i ? this.sum[j] : this.sum[j] - this.sum[i - 1];
        }
    }

}
