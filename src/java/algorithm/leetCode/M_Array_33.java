package algorithm.leetCode;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.

assume no duplicate exists in the array.
time complexity <= O(log n)

### Example
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Related : 80, 153
*/

public class M_Array_33 {

    public static void main(String... args) {
//        int[] data1 = {4, 5, 6, 7, 0, 1, 2};
//        System.out.println(search(data1, 0)); // 4
//        System.out.println(search(data1, 3)); // -1
//        System.out.println(search(data1, 4)); // 0

        int[] data2 = {5, 1, 3};
        System.out.println(search(data2, 5)); // 0
    }

    // binary search
    // time: O(logN)
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        // cannot use <=, we are looking for when left== right
        // use while (start < end) if need to exit loop first, and then use start or end to return the match
        // use while (start <= end) if you are returning the match from inside the loop
        while (left < right) {
            int middle = left + (right - left) / 2;

            //middle is biased towards left, middle will not equal to right, middle could equal to left
            // so compare middle and right
            if (nums[middle] < nums[right]) {
                // pivot is in left half
                right = middle;  // cannot use middle + 1, middle is biased towards left and need to examine nums[middle]
            } else {
                // pivot is in right half
                right = middle + 1;
            }
        }
        // now left == right, which is the index for the smallest one
        int pivot = left;

        // find target, binary search, O(logN)
        int targetIndex = -1;
        left = target > nums[nums.length - 1] ? 0 : pivot;
        right = target > nums[nums.length - 1] ? pivot - 1 : nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                targetIndex = middle;
                break;
            }
        }

        return targetIndex;
    }

    // check common solution algorithm.leetCode.M_Array_81.search1

    // https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14435/Clever-idea-making-it-simple
    public static int search1(int[] nums, int target) {
        return 1;
    }

}