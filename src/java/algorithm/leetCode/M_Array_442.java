package algorithm.leetCode;

/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array),
some elements appear twice and others appear once.
Find all the elements that appear twice in this array.

do it in O(1) space and O(n) time

### Example
[4,3,2,7,8,2,3,1] -> [2,3]

*/

import java.util.ArrayList;
import java.util.List;

public class M_Array_442 {

    public static void main(String... args) {
        System.out.println(findDuplicates1(new int[]{5, 2, 3, 1, 1})); // 1
        System.out.println(findDuplicates1(new int[]{4, 3, 2, 7, 8, 2, 3, 1})); // 2 3
    }

    // Time: O(N), Space: O(1)
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();

        // since we need to do it in O(1) space and we are allowed to modify input, use in-place marker
        // since we seek elements that appear twice, we could mark occurred element is to flip positive to negative

        for (int i = 0; i < nums.length; ++i) {
            // for each number, mark number at index number-1 to negative
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
    // Similar to 41
    public static List<Integer> findDuplicates1(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();

        // if there is no duplicate, nums[i] = i + 1
        // it also means nums[i] = nums[nums[i] - 1], use this form since it use value in nums

        for (int i = 0; i < nums.length; ++i) {
            // check nums[nums[i]-1], make sure it has the expected value
            // the missed num and the according pos will not have expected value

            // TODO: why this works?
            while (nums[nums[i] - 1] != nums[i]) {
                // otherwise, pick the next number to check
                // util nums[nums[i]-1] has the expected value
                swap(nums, i, nums[i] - 1);
            }
        }

        // if there is no duplicate, nums[i] = i + 1,
        for (int i = 0; i < nums.length; ++i) {
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