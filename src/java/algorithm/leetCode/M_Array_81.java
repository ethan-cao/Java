package algorithm.leetCode;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
You are given a target value to search. If found in the array return true, otherwise return false.


### Example
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false

Related : 33

*/

public class M_Array_81 {

    public static void main(String... args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        System.out.println(search(nums, 0)); // T
        System.out.println(search(nums, 3)); // F
    }

    public static boolean search(int[] nums, int target) {
        boolean found = false;

        return found;
    }

}