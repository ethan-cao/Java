package algorithm.leetCode;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e.,  [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
Find the minimum element.
You may assume no duplicate exists in the array.

### Example
 [3,4,5,1,2]  -> 1
 [4,5,6,7,0,1,2] -> 0

*/

public class M_BinarySearch_Array_153 {

    public static void main(String... args) {
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2}));      // 1
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));  // 0
    }

    // binary search
    // Time: O(logN), 0ms
    public static int findMin0(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            // when there is no rotation
            // equal case is when there is 1 element left
            // see findMin1 for simplification
            // basically loop until from left to right is sorted, then return nums[left]
            if (nums[left] <= nums[right]) {
                break;
            }

            int middle = left + (right - left) / 2;

            if (nums[left] <= nums[middle]) {
                left = middle + 1; // nums[middle] cannot be the minimal one
            } else {
                right = middle;  // nums[middle] could be the minimal one
            }
        }

        return nums[left];
    }

    public static int findMin1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;


        while (nums[left] > nums[right]) {
            int middle = left + (right - left) / 2;

            if (nums[left] <= nums[middle]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return nums[left];
    }

    // binary search
    // Time: O(NlogN)
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // there is no rotation
            if (nums[left] < nums[right]) {
                break;
            }

            int middle = left + (right - left) / 2;

            // since compare middle with left, we need to make sure there is rotation
            // middle is biased towards left, middle will not equal to right, middle could equal to left
            // so compare middle and left with ==
            if (nums[middle] >= nums[left]) {
                // pivot is on the right half
                left = middle + 1;
            } else {
                // pivot is on the left half
                right = middle; // middle is already biased towards left, no need to - 1
            }
        }
        // now left == right, which is the index for the smallest one, that is why use while (left < right)

        return nums[left];
    }

}
