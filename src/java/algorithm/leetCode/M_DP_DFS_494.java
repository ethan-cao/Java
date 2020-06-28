package algorithm.leetCode;

/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
Find out how many ways to assign symbols to make sum of integers equal to target S.

The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.

### Example
Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

 */

public class M_DP_DFS_494 {

    public static void main(String... args) {
        System.out.println(findTargetSumWays1(new int[]{1, 1, 1, 1, 1}, 3));  // 5
    }

    // DFS, Brute Force, 500ms
    // Time: O(2^n), Space: O(n^2)
    public static int findTargetSumWays(int[] nums, int S) {
        if (nums.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum < S) {
            return 0;
        }

        return find(nums, S, 0, 0, 0);
    }

    private static int find(int[] nums, int S, int sum, int count, int idx) {
        if (idx == nums.length) {
            return sum == S ? count + 1 : 0;
        }

        int currentCount = count;
        count += find(nums, S, sum + nums[idx], currentCount, idx + 1);
        count += find(nums, S, sum - nums[idx], currentCount, idx + 1);

        return count;
    }

    // DP, 0-1 knapsack, iterative, 3ms
    // Sum(Positive) - Sum(Negative) = S ->  Sum(Positive) = (S + Sum) / 2
    // so, it is to find how many ways to get a subset that sums up to (S +  Sum) / 2
    public static int findTargetSumWays1(int[] nums, int S) {
        int L = nums.length;
        if (L == 0) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < L; i++) {
            sum += nums[i];
        }

        // if S + sum is not even or S > sum, not possible
        if ((S + sum) % 2 != 0 || S > sum) {
            return 0;
        }

        int target = (sum + S) / 2;

        // counts[i][j]: how many counts to get sum j with the first i element
        int[][] counts = new int[L + 1][target + 1];
        counts[0][0] = 1;

        for (int i = 1; i <= L; ++i) {
            counts[i][0] = 1;

            for (int j = 0; j <= target; ++j) {

                if (j >= nums[i - 1]) {
                    // either don't use i or use i
                    counts[i][j] = counts[i - 1][j] + counts[i - 1][j - nums[i - 1]];
                } else {
                    // don't use i
                    counts[i][j] = counts[i - 1][j];
                }
            }
        }

        return counts[L][target];
    }

    // DP, 0-1 knapsack, iterative, 2ms
    public static int findTargetSumWays2(int[] nums, int S) {
        int L = nums.length;
        if (L == 0) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < L; i++) {
            sum += nums[i];
        }

        // if S + sum is not even or S > sum, not possible
        if ((S + sum) % 2 != 0 || S > sum) {
            return 0;
        }

        int target = (sum + S) / 2;

        int[] counts = new int[target + 1];
        counts[0] = 1;

        for (int i = 0; i < L; i++) {
            for (int j = target; j >= nums[i]; --j) { // backwards
                counts[j] = counts[j] + counts[j - nums[i]];
            }
        }

        return counts[target];
    }

    // DP, 0-1 knapsack, recursive, ms
    public static int findTargetSumWays3(int[] nums, int S) {
        return 1;
    }

    // TODO
    public int findTargetSumWays0(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S) {
            return 0;
        }
        int max = 2 * sum + 1;
        int offset = sum;
        int[] ways = new int[max];
        ways[offset] = 1;
        for (int num : nums) {
            int[] temp = new int[max];
            for (int i = num; i < max - num; i++) {
                temp[i + num] += ways[i];
                temp[i - num] += ways[i];
            }
            ways = temp;
        }
        return ways[S + offset];
    }
}