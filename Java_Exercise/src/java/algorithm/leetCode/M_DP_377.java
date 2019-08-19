package algorithm.leetCode;

/*
Given an integer array with all positive numbers and no duplicates,
find the number of possible combinations that add up to a positive integer target.

### Example
nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.

Sub problem

*/


public class M_DP_377 {
    public static void main(String... args) {
        int[] nums1 = {1, 2, 3};
        System.out.println(combinationSum4(nums1, 4)); // 7

        int[] nums2 = {1, 2, 3};
        System.out.println(combinationSum4(nums2, 5)); // 13
    }

    public static int combinationSum4(int[] nums, int target) {


        return 1;
    }

}
