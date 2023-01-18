package algorithm.leetCode;

/*
Given an array of integers nums and a positive integer k,
find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.

### Example
nums = [4, 3, 2, 3, 5, 2, 1], k = 4 ->  True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

### Key
0-1 knapsack

*/

import java.util.Arrays;
import java.util.stream.IntStream;

public class M_DFS_DP_698 {

    public static void main(String... args) {
        System.out.println(canPartitionKSubsets2(new int[]{4, 3, 2, 3, 5, 2, 1}, 4)); // T
        System.out.println(canPartitionKSubsets2(new int[]{4, 3, 2, 3, 5, 2, 1}, 5)); // F
        System.out.println(canPartitionKSubsets2(new int[]{2, 2, 2, 2, 3, 4, 5}, 5)); // F
        System.out.println(canPartitionKSubsets2(new int[]{18, 20, 39, 73, 96, 99, 101, 111, 114, 190, 207, 295, 471, 649, 700, 1037}, 4)); // T
    }

    // Backtracking, DFS
    // Space: O(n),  Time: O(k*2^n)
    // it takes the inner recursion 2^n time to find a good subset. Once the 1st subset is found,
    // we go on to find the second, which would take 2^n roughly (because some numbers have been marked as visited).
    // So T = 2^n + 2^n + 2^n + ... = k * 2^n.
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (k < nums.length) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int targetSum = sum / k;

        return canPartition(nums, 0, 0, targetSum, k, new boolean[nums.length]);
    }

    // if nums[idx...end] can be partitioned into k bucket with sum equal to targetSum
    private static boolean canPartition(int[] nums, int idx, int sum, int targetSum, int k, boolean[] used) {
        // if there is only 1 bucket left, since all the rest reaches target, the last one reaches targetSum for sure
        if (k == 1) {
            return true;
        }

        if (sum == targetSum) {
            // if the current bucket reaches target sum, 
            // reset idx to 0, sum to 0, look for next bucket (k-1)
            return canPartition(nums, 0, 0, targetSum, k - 1, used);
        } else if (sum > targetSum) {
            return false;
        } else {
            for (int i = idx; i < nums.length; ++i) {
                if (used[i]) {
                    continue;
                }

                // try
                used[i] = true;

                if (canPartition(nums, i + 1, sum + nums[i], targetSum, k, used)) {
                    return true;
                }

                // if not work, backtrack
                used[i] = false;
            }

            return false;
        }
    }

    // DP, Bit Masking
    // Time complexity : O(n * 2^n)
    // https://www.hackerearth.com/zh/practice/algorithms/dynamic-programming/bit-masking/tutorial/
    public static boolean canPartitionKSubsets2(int[] nums, int k) {
        int sum = IntStream.of(nums).sum();
        final int L = nums.length;
        int targetSum = sum / k;

        if (L < k || sum % k != 0) {
            return false;
        }

        // create a bitmask, whose length is 1 more digits than the array length
        final int SIZE = 1 << L;

        // used[i] : mark number used in subset, if i-th bit is set to T, i-th element is used
        boolean[] used = new boolean[SIZE];
        // total[i] : sum of subset used[i], with sum less than equal to targetSum
        int[] sums = new int[SIZE];

        // Optimization
        Arrays.sort(nums);
        if (nums[L - 1] > targetSum) {
            return false;
        }

        // start using the 1st element
        used[0] = true;

        // examine all possible bitmasking situation
        for (int i = 0; i < SIZE; ++i) {
            // if the mask bit is not set, the number is not used, skip
            if (!used[i]) {
                continue;
            }

            // examine each num
            for (int j = 0; j < L; ++j) {

                // try using the j-th element, so set j-th bit and get subset
                int temp = i | (1 << j);

                // if get a new subset
                if (temp != i) {
                    if (nums[j] + (sums[i] % targetSum) <= targetSum) {
                        // if total sum is less than target store by so far, pick the number and add to sum
                        used[temp] = true;
                        sums[temp] = sums[i] + nums[j];
                    } else {
                        // if exceed targetSum
                        break;
                    }
                }
            }
        }

        return used[SIZE - 1];
    }
}
