package algorithm.leetCode;

import java.util.Arrays;

/*
Given an integer array, you need to find one continuous subarray
that if you only sort this subarray in ascending order,
then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length

Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.

### Example
[2, 6, 4, 8, 10, 9, 15] -->  5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to
make the whole array sorted in ascending order.

*/

public class E_Array_581 {

    public static void main(String... args) {
        int[] input = {2, 6, 4, 8, 10, 9, 15}; // --> 5
//        int[] input = {-1, -100, 3, 99}; // --> 2
//        int[] input = {1, 3, 6, 4, 2, 8, 15}; // --> 4
//        int[] input = {1, 2, 3, 4};  // --> 0
//        int[] input = {5, 4, 3, 2};  // --> 4
//        int[] input = {1, 3, 2};  // --> 2
//        int[] input = {};
//        int[] input = null;

        int result = findUnsortedSubarray(input);

        System.out.println(result);
    }

    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < nums.length - 1; ++i) {
            int localMin = Arrays.stream(nums, i, nums.length).min().getAsInt();

            if (nums[i] != localMin) {
                startIndex = i;
                break;
            }
        }

        for (int i = nums.length; i > 0; --i) {
            int localMax = Arrays.stream(nums, 0, i).max().getAsInt();

            if (nums[i - 1] != localMax) {
                endIndex = i - 1;
                break;
            }
        }

        return endIndex == startIndex ? 0 : endIndex - startIndex + 1;
    }

    // O(n) runtime, O(1) space
    public static int findUnsortedSubarray1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        // make difference between startIndex and endIndex 1 by default
        int startIndex = -1;
        int endIndex = -2;
        int localMax = nums[0];
        int localMin = nums[nums.length - 1];

        // make 2 iteration within 1 iteration
        for (int i = 1; i < nums.length; ++i) {
            // forward iteration
            localMax = Math.max(nums[i], localMax);
            if (nums[i] < localMax) {
                endIndex = i;
            }

            // backward iteration
            localMin = Math.min(nums[nums.length - 1 - i], localMin);
            if (nums[nums.length - 1 - i] > localMin) {
                startIndex = nums.length - 1 - i;
            }
        }

        return endIndex - startIndex + 1;
    }

    public static int findUnsortedSubarray2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        // make difference between startIndex and endIndex 1 by default
        int startIndex = -1;
        int endIndex = -2;

        int[] numsCopy = new int[nums.length];
        System.arraycopy(nums, 0, numsCopy, 0, nums.length);
        Arrays.sort(numsCopy);

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != numsCopy[i]) {

                if (startIndex == -1) {
                    startIndex = i;  // update start index only once
                }

                endIndex = i;
            }
        }

        return endIndex - startIndex + 1;
    }

}
