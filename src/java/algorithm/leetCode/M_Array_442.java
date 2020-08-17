package algorithm.leetCode;

/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array),
some elements appear twice and others appear once.
Find all the elements that appear twice in this array.

### Example
[4,3,2,7,8,2,3,1] -> [2,3]

*/

import java.util.*;

public class M_Array_442 {

    public static void main(String... args) {
        System.out.println(findDuplicates1(new int[]{5, 2, 3, 1, 1})); // 1
        System.out.println(findDuplicates1(new int[]{4, 3, 2, 7, 8, 2, 3, 1})); // 2 3
    }

    // Time: O(N), Space: O(1)
    // 5ms
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();

        for (int i = 0; i < nums.length; ++i) {
            // for each occurred number, mark number at index number-1 to negative
            // if number at index number-1 is already negative, the number appeared before

            int idx = Math.abs(nums[i]) - 1;

            if (nums[idx] < 0) {
                duplicates.add(Math.abs(nums[i]));
            }

            nums[idx] = -nums[idx];
        }

        return duplicates;
    }

    // Time: O(N), Space: O(1)
    // 4ms
    // Similar to 41
    public static List<Integer> findDuplicates1(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();

        // there are n slots and n possible values to distribute
        // if there is no duplicate, it is possible to have distribution: nums[i] = i + 1
        // then i = nums[i] - 1 so nums[i] = nums[nums[i] - 1]

        // start re-distributing to make sure nums[i] = nums[nums[i] - 1]
        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] != nums[nums[i] - 1]) {
                // each swap either moves nums[i] to nums[i]-1 or i-1
                // Therefore, at most loop 2n times in this while loop.
                swap(nums, i, nums[i] - 1);
            }
        }

        // now, nums[i] is supposed to be i + 1, if no duplicate
        for (int i = 0; i < nums.length; ++i) {
            // if nums[i] is not nums[i] = i + 1, that is duplicate
            if (nums[i] != i + 1) {
                duplicates.add(nums[i]);
            }
        }

        return duplicates;
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}