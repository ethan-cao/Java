package algorithm.leetCode;

/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.

### Example
Input: s = 7, nums = [2,3,1,2,4,3] -> 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

*/

public class M_2Pointer_Array_209 {

    public static void main(String... args) {
//        System.out.println(minSubArrayLen1(7, new int[]{2, 3, 1, 2, 4, 3}));  // 2
//        System.out.println(minSubArrayLen1(3, new int[]{1, 1}));              // 0
//        System.out.println(minSubArrayLen1(7, new int[]{1, 1, 7, 1, 1}));     // 1
//        System.out.println(minSubArrayLen1(11, new int[]{1, 2, 3, 4, 5}));    // 3
        System.out.println(minSubArrayLen1(15, new int[]{1, 2, 3, 4, 5}));    // 5
    }

    // 2 pointer
    // Time: O(N)
    // in the worst case, pointer right moves forward n times and pointer left also moves forward n times.
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        boolean hasMinLength = false;
        int minLength = nums.length;
        int sum = 0;

        // since we are looking for subarray length, naturally think about using 2 pointer
        // subarray means contiguous elements, use left and right index narrow the window
        int left = 0;
        int right = 0;

        while (right < nums.length) {
            sum += nums[right];

            // since nums only contains positive, sums is strictly increasing
            // no need e to include more elements once sum >= s
            while (sum >= s) {
                hasMinLength = true;
                minLength = Math.min(minLength, right - left + 1);

                if (minLength == 1) {
                    return 1; // 1 is the shortest possible case
                }

                // try moving left, see if we can shorted length with sun >= s
                sum -= nums[left];
                left++;
            }

            right++;
        }

        return hasMinLength ? minLength : 0;
    }

    // Time: O(NlogN)
    // logN remind one of binary search, and sums[] is suitable for binary search
    public static int minSubArrayLen1(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // sum[i] is sum til to nums[i-1] (inclusive)
        // since nums only contains positive, sums[] is strictly increasing (sorted), suitable for binary search

        // we need left and right for sub-array
        // sums[left] + t = sums[right], as long as t >= s, so looking for the smallest t that is larger than s
        // t = sums[left+1] + ... + sums[right],  the length is right - (left + 1) + 1 = right - left
        // left+1 could be 0, basically t = sums[right]
        // thus, add 1 more in beginning of sums[]
        // sum[0] is 0, sum[1] is nums[1]

        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; ++i) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        boolean hasMinLength = false;
        int minLength = nums.length;

        // given a left for sums[], right can be searched using binary search
        // if there is an index right exists between [left+1, sums.length-1]
        // and the corresponding value is larger or equal to sums[left] + s, then sum between [left+1, right] is s
        for (int left = 0; left < sums.length; ++left) {
            int right = search(sums, left + 1, sums.length - 1, sums[left] + s);

            if (right != sums.length) {
                hasMinLength = true;
                minLength = Math.min(minLength, right - left);
            }
        }

        return hasMinLength ? minLength : 0;
    }

    // Binary search
    private static int search(int[] sums, int left, int right, int target) {
        int targetIdx = sums.length;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            // find the smallest one that is larger than target
            if (middle - 1 >= 0 && sums[middle] >= target && sums[middle - 1] < target) {
                return middle;
            }

            if (sums[middle] >= target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return targetIdx;
    }

}