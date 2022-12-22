package algorithm.leetCode;

/*
Given a non-empty array containing only positive integers,
find if the array can be partitioned into 2 subsets such that the sum of elements in both subsets is equal.

1 <= nums.length <= 200
1 <= nums[i] <= 100

### Example
[1, 2, 3, 5] -> false
[1, 5, 11, 5] -> true
Explanation: The array can be partitioned as [1, 5, 5] and [11]

*/

public class M_DP_Array_416 {

    public static void main(String... args) {
        System.out.println(canPartition_1(new int[]{1, 5, 11, 5}));    // T
        System.out.println(canPartition_1(new int[]{1, 2, 3, 5}));     // F
        System.out.println(canPartition_1(new int[]{3, 3, 3, 4, 5}));  // T
    }

    // O(2^N)
    public static boolean canPartition_1(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int half = sum / 2;
        Boolean[][] memo = new Boolean[nums.length][half + 1];

        return partition_1(nums, nums.length - 1, half, memo);
//        return partition_2(nums, 0, half, memo);
    }

    // DP, top down
    // search from the end
    // 10ms
    private static boolean partition_1(int[] nums, int idx, int target, Boolean[][] memo) {
        // BASE
        // canPartition(i, 0) = true
        if (target == 0) {
            return true;
        }

        if (idx < 0) {
            return false;
        }

        if (memo[idx][target] != null) {
            return memo[idx][target];
        }

        // TRANSFORM
        // skip(i) = canPartition(i-1, target)
        // take(i) = canPartition(i-1, target - nums[i])
        // canPartition(i) = skip(i) || take(i)
        boolean skip = partition_1(nums, idx - 1, target, memo);
        boolean take = target - nums[idx] >= 0 ? partition_1(nums, idx - 1, target - nums[idx], memo) : false;

        memo[idx][target] = take || skip;

        return memo[idx][target];
    }

    // DFS
    // search from the start
    // 5ms
    private boolean partition_2(int[] nums, int idx, int sum, Boolean[][] memo) {
        if (sum == 0) {
            return true;
        }

        if (idx == nums.length - 1) {
            return false;
        }

        if (memo[idx][sum] != null) {
            return memo[idx][sum];
        }

        if (nums[idx] <= sum) {
            memo[idx][sum] = partition_2(nums, idx + 1, sum - nums[idx], memo) || partition_2(nums, idx + 1, sum, memo);
        } else {
            memo[idx][sum] = partition_2(nums, idx + 1, sum, memo);
        }

        return memo[idx][sum];
    }


    // DP iterative
    // 39ms
    public static boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int half = sum / 2;

        // sums[i][j]: if there are elements located from 0 to i, that can sum up to j
        // if, some elements can sum up to half, then the reset sum up to half as well, possible to partition
        final int L = nums.length;
        boolean[][] results = new boolean[L][half + 1];

        // BASE
        // canPartition(i, 0) = true
        for (int i = 0; i < L; ++i) {
            results[i][0] = true;
        }

        for (int i = 0; i < L; ++i) {
            int num = nums[i];

            for (int target = 1; target <= half; ++target) {

                // TRANSFORM
                // take = canPartition(i - 1, half - nums[i])
                // skip = canPartition(i - 1, half)
                // canPartition(i, half) = take || skip
                boolean skip = i - 1 >= 0 ? canPartitions[i - 1][target] : false;
                // each num can ONLY BE USED ONCE, different from 377 and 518
                boolean take = i - 1 >= 0 && target - num >= 0 ? canPartitions[i - 1][target - num] : false;

                canPartitions[i][target] = skip || take;
            }
        }

        return results[L - 1][half];
    }

    // DP iterative, condensed space
    // 20 ms
    public static boolean canPartition1(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // if sum is odd number, then not possible
        if (sum % 2 != 0) {
            return false;
        }

        final int L = nums.length;
        int half = sum / 2;
        boolean[] results = new boolean[half + 1];

        results[0] = true;

        // !!! iterate backwards
        // [i][j] depends on [i-1][j-num]
        // j depends on j - num
        // row i should be derived from the previous row
        for (int i = 0; i < L; ++i) {
            for (int target = half; target >= 0; --target) {
                boolean take = false;
                boolean skip = false;

                if (i == 0) {
                    take = nums[i] == target;
                    skip = false;
                } else {
                    take = target - nums[i] >= 0 ? results[target - nums[i]] : false;
                    skip = results[target];
                }

                results[target] = take || skip;
            }
        }

        return results[half];
    }

}
