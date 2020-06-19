package algorithm.leetCode;

/*
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
You may assume all input has valid answer.

Can you do it in O(n) time and/or in-place with O(1) extra space?

### Example
[1, 5, 1, 1, 6, 4] -> One possible answer is [1, 4, 1, 5, 1, 6]
[1, 3, 2, 2, 3, 1] -> One possible answer is [2, 3, 1, 3, 1, 2].

*/

import java.util.*;

public class M_Sort_Array_324 {

    public static void main(String... args) {
        int[] nums = new int[]{1, 5, 1, 1, 6, 4};

        wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void wiggleSort(int[] nums) {

    }

}
