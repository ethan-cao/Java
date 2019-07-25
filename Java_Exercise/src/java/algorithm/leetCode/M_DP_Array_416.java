package algorithm.leetCode;

/*
Given a non-empty array containing only positive integers,
find if the array can be partitioned into 2 subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.


### Example
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11]

Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
*/


import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class M_DP_Array_416 {

    public static void main(String... args) {
        int[] nums1 = {1, 5, 11, 5};
//        System.out.println(canPartition(nums1));  // t
//
//        int[] nums2 = {1, 2, 3, 5};
//        System.out.println(canPartition(nums2));  // f

        int[] nums3 = {1, 3, 6, 1};
        System.out.println(canPartition(nums3));  // f
    }

    public static boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int sum = Arrays.stream(nums).sum();

        // if sum is odd number, then not possible
        if (sum % 2 != 0){
            return false;
        }

        int half = sum / 2;


        // need to find n elements from nums, which can add up to half
        // knapsack problem

        return false;
    }

    // Brute force
    public static boolean canPartition1(int[] nums) {
        return false;
    }
}