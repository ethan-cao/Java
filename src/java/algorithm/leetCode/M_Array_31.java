package algorithm.leetCode;

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers
If such arrangement is not possible, it must rearrange it as the lowest possible order
(ie, sorted in ascending order).
The replacement must be in-place and use only constant extra memory.

### Example
1,2,3 → 1,3,2
for 1, 2 and 3, there are P(3,3) = 6 permutations : 123, 132, 213, 231, 312, 321
the given sequence is 123, the next lexicographically greater permutation is 132

3,2,1 → 1,2,3 :  3,2,1 is the last permutation, so return the 1st one  1,2,3
1,1,5 → 1,5,1

*/

import java.util.*;

public class M_Array_31 {

    public static void main(String... args) {
        int[] nums = {1, 2, 3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // 1 3 2

        int[] nums1 = {3, 2, 1};
        nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1)); // 1 2 3

        int[] nums2 = {2, 3, 1, 3, 3};
        nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2)); // 2,3,3,1,3

        int[] nums3 = {1, 4, 6, 66, 23, 21, 0};
        nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3)); // 1,4,21,0,6,23,66
    }

    // Stack, 1ms
    // Time: O(N)
    /* strictly increasing sequence is the largest permutation,
     * strictly decreasing sequence is the smallest permutation
     * lexicographically = alphabetical order
     * if input is [1,2,5,4,3], 2 (idx = 1) is the left idx for local max 5 (idx = 2),
     * the right most larger (than 2) one is 3 (idx = 4), swap 2 and 3, we have [1,3,5,4,2]
     * sort [5,4,2], so we have [1,3,2,4,5]
     */
    public static void nextPermutation(int[] nums) {
        final int L = nums.length;
        int leftOnLocalMaxIdx = -1;
        int rightMostLargerIdx = -1;
        Deque<Integer> stack = new ArrayDeque<>(); // monotonic increasing

        // from right to left, find the first local maximum's left idx
        for (int i = L - 1; i >= 0; --i) {
            int num = nums[i];

            if (!stack.isEmpty() && nums[stack.peek()] > num) {
                leftOnLocalMaxIdx = i;
                break;
            }

            stack.push(i);
        }

        if (leftOnLocalMaxIdx == -1) {
            reverse(nums, 0, L - 1);
        } else {
            // find the right most larger item of the local max
            while (!stack.isEmpty() && nums[stack.peek()] > nums[leftOnLocalMaxIdx]) {
                rightMostLargerIdx = stack.pop();
            }

            swap(nums, leftOnLocalMaxIdx, rightMostLargerIdx);
            reverse(nums, leftOnLocalMaxIdx + 1, L - 1);
        }
    }

    private static void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    private static void reverse(int[] nums, int idx1, int idx2) {
        while (idx1 < idx2) {
            swap(nums, idx1, idx2);
            idx1++;
            idx2--;
        }
    }

    // find the previous permutation
    private static void previousPermutation(int[] nums) {

    }
}
