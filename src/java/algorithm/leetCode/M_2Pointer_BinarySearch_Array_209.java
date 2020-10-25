package algorithm.leetCode;

/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.

### Example
Input: s = 7, nums = [2,3,1,2,4,3] -> 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

*/

public class M_2Pointer_BinarySearch_Array_209 {

    public static void main(String... args) {
        System.out.println(minSubArrayLen1(7, new int[]{2, 3, 1, 2, 4, 3}));  // 2
        System.out.println(minSubArrayLen1(3, new int[]{1, 1}));              // 0
        System.out.println(minSubArrayLen1(7, new int[]{1, 1, 7, 1, 1}));     // 1
        System.out.println(minSubArrayLen1(11, new int[]{1, 2, 3, 4, 5}));    // 3
        System.out.println(minSubArrayLen1(15, new int[]{1, 2, 3, 4, 5}));    // 5
    }

    // 2 pointer, Time: O(N), 1ms
    // in the worst case, pointer right moves forward n times and pointer left also moves forward n times.
    public static int minSubArrayLen0(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int minLength = Integer.MAX_VALUE;
        int sum = 0;

        int left = 0;
        int right = 0;

        while (right < nums.length) {
            sum += nums[right];

            // since nums only contains positive, sums is strictly increasing
            // no need e to include more elements once sum >= s
            while (sum >= s) {
                minLength = Math.min(minLength, right - left + 1);

                if (minLength == 1) {
                    return 1; // 1 is the shortest possible case
                }

                sum -= nums[left];
                left++;
            }

            right++;
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // Binary Search, Time O(NlogN), 5ms
    public static int minSubArrayLen1(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] sums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sums[i + 1] = sums[i] + nums[i];
        }

        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < sums.length; ++i) {
            int right = search(sums, i + 1, sums.length - 1, s + sums[i]);

            if (right != sums.length) {
                minLength = Math.min(minLength, right - i);
            }

            // optimization
            if (minLength == 1) {
                return 1;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    private static int search1(int[] sums, int left, int right, int target) {
        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (sums[middle] >= target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return left;
    }

    // Binary Search, Time O(NlogN), 5ms
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // sum[i] is sum til to nums[i-1] (inclusive)
        // since nums contains positive, sums[] is strictly increasing (sorted), suitable for binary search

        // we need left and right for sub-array
        // sums[left] + t = sums[right], t = sums[left+1] + ... + sums[right]
        // looking for the smallest length for t, so t >= s
        // length for t is from left + 1 til right: right - (left + 1) + 1 = right - left

        // why sums.length = nums.length + 1;
        // left+1 can be 0, basically t is the sum of all num in nums, namely sums[right]
        // thus, add 1 more in beginning of sums[], sum[0] is 0, sum[1] is nums[1]
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; ++i) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int minLength = Integer.MAX_VALUE;

        // given a left for sums[], right can be searched using binary search
        // if there is an index right exists between [left+1, sums.length-1]
        // and the corresponding value >= sums[left] + s , then sum between [left+1, right] is  >= s
        for (int left = 0; left < sums.length; ++left) {
            int right = search(sums, left + 1, sums.length - 1, sums[left] + s);

            if (right != -1) {
                minLength = Math.min(minLength, right - left);
            }
        }

        // !!!check if minLength is Integer.MAX_VALUE
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // Binary search, search for right boundary
    private static int search(int[] sums, int left, int right, int target) {
        while (left <= right) {
            int middle = left + (right - left) / 2;

            // find the smallest one that is >= target
            if (sums[middle] >= target && middle - 1 >= 0 && sums[middle - 1] < target) {
                return middle;
            }

            if (sums[middle] >= target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1;
    }

}
