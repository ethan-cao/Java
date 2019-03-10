package leetCode;

/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

### Input
int[]

### Output
int

### Example
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3

### Condition
i<=j
You may assume that the array does not change.
There are many calls to sumRange function.

### Essential problem

### Corner case

 */
public class E_Array_303 {
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};

        NumArray obj = new NumArray(nums);
        int result = obj.sumRange(2, 6);

        System.out.println(result);
    }

    static class NumArray {
        private int[] sum;

        public NumArray(int[] nums) {
            this.sum = new int[nums.length];

            if (0 == nums.length) {
               return;
            }

            this.sum[0] = nums[0];
            for (int i = 1; i < nums.length; ++i) {
                this.sum[i] = this.sum[i - 1] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            int sum = 0;

            if ( i > j || i < 0 || j > this.sum.length - 1) {
                return sum;
            }

            return 0 == i ? this.sum[j] : this.sum[j] - this.sum[i - 1];
        }
    }

}
