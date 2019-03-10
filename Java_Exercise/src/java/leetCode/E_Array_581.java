package leetCode;

import java.util.Arrays;

/*
Given an integer array, you need to find one continuous subarray
that if you only sort this subarray in ascending order,
then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length

### Input
int[] array, int k

### Output
int[]

### Example
[2, 6, 4, 8, 10, 9, 15] -->  5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to
make the whole array sorted in ascending order.

### Condition
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.

### Essential problem

### Corner case

*/
public class E_Array_581 {

    public static void main(String... args) {
//        int[] input = {2, 6, 4, 8, 10, 9, 15}; // --> 5
//        int[] input = {-1, -100, 3, 99}; // --> 2
//        int[] input = {1, 3, 6, 4, 2, 8, 15}; // --> 4
//        int[] input = {1, 2, 3, 4};  // --> 0
//        int[] input = {5, 4, 3, 2};  // --> 4
        int[] input = {1, 3, 2};  // --> 2
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

        for (int i = 0; i <= nums.length - 2; ++i) {
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

    public static int findUnsortedSubarray1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i <= nums.length - 2; ++i) {
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
}
