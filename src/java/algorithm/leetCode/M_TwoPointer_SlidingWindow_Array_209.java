package algorithm.leetCode;

/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.

### Example
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

*/

public class M_TwoPointer_SlidingWindow_Array_209 {

    public static void main(String... args) {
        System.out.println(minSubArrayLen1(7, new int[]{2, 3, 1, 2, 4, 3}));  // 2
        System.out.println(minSubArrayLen1(3, new int[]{1, 1}));  // 0
        System.out.println(minSubArrayLen1(7, new int[]{1, 1, 7, 1, 1}));  // 1
        System.out.println(minSubArrayLen1(11, new int[]{1, 2, 3, 4, 5}));  // 3
    }

    // Time: O(n)
    // in the worst case, pointer right moves forward n times and pointer left also moves forward n times.
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int minLength = nums.length;
        boolean hasMinLength = false;
        int sum = 0;

        // since we are looking for subarray length, naturally think about using 2 pointer
        // subarray means contiguous elements, use left and right index narrow the window
        // you cannot sort as the current order actually matters
        for (int left = 0, right = 0; right < nums.length; ++right) {
            sum += nums[right];

            while (sum >= s) {
                hasMinLength = true;

                // length of current subarray is (rigth - left + 1)
                minLength = Math.min(minLength, right - left + 1);

                sum -= nums[left];
                left++;
            }
        }

        return hasMinLength ? minLength : 0;
    }

    // Time: O(NlogN)
    public static int minSubArrayLen1(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // since nums only contains positive, sums is strictly increasing
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; ++i) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int minLength = nums.length;
        boolean hasMinLength = false;

        for (int left = 0; left < nums.length; ++left) {
            // look for sums[left] + s, which is surely >= s
            int right = search(sums, left + 1, sums.length - 1, sums[left] + s);

            if (right != -1) {
                hasMinLength = true;
                minLength = Math.min(minLength, right - left);
            }
        }

        return hasMinLength ? minLength : 0;
    }

    private static int search(int[] nums, int left, int right, int target) {
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

        return -1;
    }

}

