package algorithm.leetCode;

/*
Given an array with n integers, your task is to check
if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for
every i (1 <= i < n).

### Input
int[]

### Output
boolean

### Example
[4,2,3] -->  True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array [1, 2, 3]

[4,2,1] -->  False
Explanation: You can't get a non-decreasing array by modify at most one element.

### Condition
The n belongs to [1, 10,000].

### Essential problem
greedy problem

### Corner case

*/
public class E_Array_665 {
    public static void main(String... args) {
//        int[] input = {4, 2, 3};  // --> true
//        int[] input = {3, 4, 2, 3}; // --> false;
//        int[] input = {3, 5, 4, 3};  // --> false
//        int[] input = {3, 5, 4, 4};  // --> true
//        int[] input = {4, 2, 1}; // --> false
//        int[] input = {1,23,4,5,2,2}; // --> false
        int[] input = {2, 2, 5, 5, 5, 4, 6}; // --> true

        System.out.println(checkPossibility(input));
    }

    // https://leetcode.com/problems/non-decreasing-array/discuss/106826/JavaC++-Simple-greedy-like-solution-with-explanation
    public static boolean checkPossibility(int[] nums) {
        if (nums == null) {
            return false;
        }

        if (nums.length <= 1) {
            return true;
        }

        int numberOfChanges = 0;

        for (int i = 1; i < nums.length && numberOfChanges < 2; ++i) {
            if (nums[i] < nums[i - 1]) {
                if (i >= 2 && nums[i - 2] >= nums[i]) {
                    nums[i] = nums[i - 1];
                } else {
                    nums[i - 1] = nums[i];
                }

                numberOfChanges++;
            }
        }

        return numberOfChanges <= 1;
    }
}
