package algorithm.leetCode;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.
You may assume no duplicate exists in the array.

### Example
 [3,4,5,1,2]  -> 1
 [4,5,6,7,0,1,2] -> 0

Related : 33, 81

*/

public class M_Array_153 {

    public static void main(String... args) {
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2}));      // 1
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));  // 0
    }

    // binary search
    // Time: O(n)
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

    // binary search
    // Time: O(n)
    public static int findMin1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // cannot use <=, we are looking for when left== right
        // use while (start < end) if need to exit loop first, and then use start or end to return the match
        // use while (start <= end) if you are returning the match from inside the loop
        while (left < right) {
            int middle = left + (right - left) / 2;

            //middle is biased towards left, middle will not equal to right, middle could equal to left
            // so compare middle and right without ==
            if (nums[middle] < nums[right]) {
                // pivot is in left half
                right = middle;  // cannot use middle + 1, middle is biased towards left and need to examine nums[middle]
            } else {
                // pivot is in right half
                right = middle + 1;
            }
        }
        // now left == right, which is the index for the smallest one

        return nums[left];
    }
}