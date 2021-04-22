package algorithm.leetCode;

/*
Given a sorted array of distinct integers and a target value,
return the index if the target is found.
If not, return the index where it would be if it were inserted in order

### Example
Input: nums = [1,3,5,6], target = 5  -> 2
Input: nums = [1,3,5,6], target = 2  -> 1
Input: nums = [1,3,5,6], target = 7  -> 4
Input: nums = [1,3,5,6], target = 0  -> 0

*/

public class E_BinarySearch_35 {

    // search for boundary, the left boundary that is >= target
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] == target) {
                return middle;
            }

            if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        // no need to check if left > nums.length
        return left;
    }

}
