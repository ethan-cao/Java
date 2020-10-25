package algorithm.leetCode;

/*
A peak element is an element that is greater than its neighbors.
Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
You may imagine that nums[-1] = nums[n] = -∞.

### Example
[1,2,3,1]  -> 2
[1,2,1,3,5,6,4] -> 1 or 5

*/

public class M_BinarySearch_Array_162 {

    public static void main(String... args) {
        System.out.println(findPeakElement(new int[]{3, 2, 1}));              // 0
        System.out.println(findPeakElement(new int[]{3, 2, 1}));              // 0
        System.out.println(findPeakElement(new int[]{1, 2, 3, 1}));           // 2
        System.out.println(findPeakElement(new int[]{1, 3, 2, 1}));           // 1
        System.out.println(findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));  // 1 or 5
    }

    // binary search
    // Time: O(logN) 0ms
    public static int findPeakElement0(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            if (right - left <= 1) {
                break;
            }

            int middle = left + (right - left) / 2;

            if (nums[middle] > nums[middle - 1] && nums[middle] > nums[middle + 1]) {
                return middle;
            }

            if (nums[middle] < nums[middle + 1]) {
                left = middle + 1;
            } else if (nums[middle] < nums[middle - 1]) {
                right = middle - 1;
            }
        }

        return nums[left] > nums[right] ? left : right;
    }

    // binary search
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int middle = left + (right - left) / 2;

            if (middle >= 1 && nums[middle] < nums[middle - 1]) {
                right = middle - 1;
            } else if (nums[middle] < nums[middle + 1]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }

        return left;
    }

    // binary search
    private static int findPeakElement1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int middle = left + (right - left) / 2;

            // since we use condition middle < nums.length -1, case left = nums.length - 1 is skiped
            if (middle < nums.length - 1 && nums[middle] <= nums[middle + 1]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        // since case left = nums.length - 1 is skipped, need to check it later
        if (left == nums.length - 1 && nums.length - 2 >= 0) {
            return nums[nums.length - 1] > nums[nums.length - 2] ? nums.length - 1 : nums.length - 2;
        } else {
            return left;
        }
    }

}