package algorithm.leetCode;

/*
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000

### Example
[1, 2, 5], 0         -> 0 
[1, 1, 1, 1, 1], 3   -> 5
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

 */

public class M_DP_DFS_494 {

    public static void main(String... args) {
        System.out.println(findTargetSumWays_1(new int[]{1, 1, 1, 1, 1}, 3));  // 5
        System.out.println(findTargetSumWays_1(new int[]{1, 2, 3, 3, 1}, 6));  // 2
    }

    // DFS, Brute Force, 500ms
    // Time: O(2^n), Space: O(n^2)
    public static int findTargetSumWays_1(int[] nums, int target) {
        return find_1(nums, 0, target);
    }

    private static int find_1(int[] nums, int idx, int target) {
        if (idx == nums.length) {
            return target == 0 ? 1 : 0;
        }

        return find_1(nums, idx + 1, target - nums[idx]) +
                find_1(nums, idx + 1, target + nums[idx]);
    }

    // same as findTargetSumWays_1
    // DFS, Brute Force, 500ms
    // Time: O(2^n), Space: O(n^2)
    // no need for memoization, since we never re-calculate the same thing
    public static int findTargetSumWays_2(int[] nums, int target) {
        return find_2(nums, 0, target, 0);
    }

    private static int find_2(int[] nums, int idx, int target, int count) {
        if (idx == nums.length) {
            return target == 0 ? 1 : 0;
        }

//        !!! store the current count before continuing
        int currentCount = count;

        count += find_2(nums, idx + 1, target - nums[idx], currentCount);
        count += find_2(nums, idx + 1, target + nums[idx], currentCount);

        return count;
    }

    // DP, iterative, 15ms
    public int findTargetSumWays_3(int[] nums, int target) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        // nums contains only positive number
        if (sum < target || -sum > target) {
            return 0;
        }

        // given sum = Σ nums[i]
        // range of expression results is [-sum, sum], there are (2 * sum + 1) values in total
        // e.g. nums = [1,2,3]
        // possible expression results are     -6, -5, -4, -3, -2, -1, +0, +1, +2, +3, +4,  +5, +6
        // put them in array, their index is    0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10,  11, 12
        // 13 elements in total (13 = 2 * sum + 1)
        // !!! index = expressionResult + sum, basically sum is the offset from expressionResult to index
        // what we need is index = target + sum
        final int L = nums.length;
        final int SIZE = 2 * sum + 1;
        final int OFFSET = sum;

        // results[i][j + OFFSET]: number of ways with the first i elements to get expression result j
        int[][] results = new int[L][SIZE];

        // BASE
        // use + nums[0]
        results[0][+nums[0] + OFFSET]++;
        // use - nums[0]
        results[0][-nums[0] + OFFSET]++; // !!! nums[0] could be 0, cannot use results[0][-nums[0] + OFFSET] + 1

        // TRANSFORM
        // usePlus(i, j) += f(i-1, j - mum), j - num >= 0
        // useMinus(i, j) += f(i-1, j + mum), j + num < SIZE
        // count(i, j) = usePlus(i, j) + useMinus(i, j)
        for (int i = 1; i < L; ++i) {
            int num = nums[i];

            for (int j = 0; j < SIZE; ++j) {

                // if we can use + with num
                if (j - num >= 0) {
                    results[i][j] += results[i - 1][j - num];
                }

                // if we can use - with num
                if (j + num < SIZE) {
                    results[i][j] += results[i - 1][j + num];
                }
            }
        }

        return results[L - 1][target + OFFSET];
    }

    public int findTargetSumWays111(int[] nums, int target) {
        int L = nums.length;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (target > sum || target < -sum) {
            return 0;
        }

        int SIZE = 2 * sum + 1;
        int OFFSET = sum;
        int[][] counts = new int[L][SIZE];

        for (int i = 0; i < L; ++i) {
            int num = nums[i];

            for (int j = 0; j < SIZE; ++j) {
                
                if (i == 0) {
                    counts[i][j] += (j == num + sum ? 1 : 0);
                    counts[i][j] += (j == -num + sum ? 1 : 0);
                } else {
                    counts[i][j] += j - num >= 0 ? counts[i - 1][j - num] : 0;
                    counts[i][j] += j + num < SIZE ? counts[i - 1][j + num] : 0;
                }
            }
        }

        return counts[L - 1][target + OFFSET];
    }

    // DP, iterative, condensed space, 15ms
    public int findTargetSumWays_4(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum < target || -sum > target) {
            return 0;
        }

        final int SIZE = 2 * sum + 1;
        int[] results = new int[SIZE];
        results[sum] = 1;

        for (int num : nums) {
            int[] temp = new int[SIZE];

            for (int i = num; i < SIZE - num; i++) {
                temp[i + num] += results[i];
                temp[i - num] += results[i];
            }

            results = temp;
        }

        return results[target + sum];
    }

    // DP, iterative, 3ms
    // 10ms
    public static int findTargetSumWays1(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum < target || -sum > target) {
            return 0;
        }

        // we need to use all nums, and some are position, some are negative
        // subset contains positive nums are P
        // subset contains negative nums are M
        // we need
        //
        // sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
        // given             sum(P) + sum(N) = sum
        //                        2 * sum(P) = target + sum
        // so                         sum(P) = (target + sum) / 2
        // basically, we need to find a subset containing positive num, whose sum is (target + sum) / 2

        if ((sum + target) % 2 != 0) {
            return 0;
        }

        final int L = nums.length;
        final int SUM = (target + sum) / 2;

        int[][] results = new int[L][SUM + 1];

        // BASE
        // count(0, 0) = 1
        results[0][0] = 1;

        for (int j = 0; j <= SUM; j++) {
            results[0][j] += nums[0] == j ? 1 : 0;
        }

        // TRANSFORM
        // skip(i, j) = count(i-1, j)
        // take(i, j) = count(i-1, j-nums[i]), j >= nums[i]
        // count(i, j) = skip(i, j) + take(i, j)
        for (int i = 1; i < L; ++i) {
            for (int j = 0; j <= SUM; ++j) {
                int num = nums[i];

                int skip = results[i - 1][j];
                int take = j >= num ? results[i - 1][j - num] : 0;

                results[i][j] = skip + take;
            }
        }

        return results[L - 1][SUM];
    }

    // DP, iterative, condensed space
    // 4ms
    public static int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum < target || -sum > target) {
            return 0;
        }

        if ((sum + target) % 2 != 0) {
            return 0;
        }

        final int L = nums.length;
        final int SUM = (target + sum) / 2;

        int[] results = new int[SUM + 1];

        results[0] = 1;
        for (int j = 0; j <= SUM; j++) {
            results[j] += nums[0] == j ? 1 : 0;
        }

        for (int i = 1; i < L; i++) {
            for (int j = SUM; j >= 0; --j) {
                int num = nums[i];

                if (j >= num) {
                    results[j] += results[j - num];
                }
            }
        }

        return results[SUM];
    }

}