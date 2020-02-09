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

import java.util.Arrays;

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

    // https://youtu.be/quAS1iydq7U
    // https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
    // the key is to analyze and figure how permutation is built and what is next permutation
    // use tree to analyze how permutation is built, from small to large
    // take set [1,2,3,4,5] for example, the smallest permutation is [1,2,3,4,5], the largest permutation is [5,4,3,2,1]
    // basically strictly increasing sequence is the largest permutation, strictly decreasing sequence is the smallest permutation
    // if input is [1,2,5,4,3], sub-sequence [5,4,3] is the largest permutation for the last 3 elements, [3,4,5] is the smallest
    // to get the next permutation, we need to replace element at index 1 with the smallest larger element in [5,4,3], which is 3
    // the [1,2,5,4,3] becomes [1,3,5,4,2].  [5,3,2] is still strictly decreasing, the largest permutation
    // then we need to make it the smallest permutation, by reverse to [2,3,5]
    // thus the next permutation is [1,3,2,4,5]
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        // find the strictly decreasing sequence
        // look for turning point, from right to left, the 1st one that is smaller than its right value
        // after turningPointIdx, the sequence is strictly decreasing
        int turningPointIdx = nums.length - 1;
        for (int i = nums.length - 1; i > 0; --i) {
            if (nums[i - 1] < nums[i]) {
                turningPointIdx = i - 1;
                break;
            }
        }

        // if turning point does not exist, there is no next permutation
        if (turningPointIdx == nums.length - 1) {
            reverse(nums, 0, nums.length - 1);
        } else {
            // seek replacementIdx for turningPointIdx to swap, the smallest one that is larger than turningPointIdx
            int replacementIdx = turningPointIdx + 1;
            for (int i = turningPointIdx + 1; i < nums.length; ++i) {
                if (nums[i] > nums[turningPointIdx] && nums[i] <= nums[replacementIdx]) { //!!! use <= to pick same value at larger index
                    replacementIdx = i;
                }
            }
            swap(nums, turningPointIdx, replacementIdx);

            // nums[turningPointIdx+1, nums.length-1] is in descending order, reserve it to get ascending order
            reverse(nums, turningPointIdx + 1, nums.length - 1);
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

}

// look for the previous permutation
class PreviousPermutation {

    public static void main(String... args) {

    }

    private static void previousPermutation(int[] nums) {

    }

}