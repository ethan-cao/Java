package algorithm.leetCode;

/*
You have n dice and each die has k faces numbered from 1 to k.

Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) 
to roll the dice so the sum of the face-up numbers equals target. 
Since the answer may be too large, return it modulo 10^9 + 7.

1 <= n, k <= 30
1 <= target <= 1000

### Example
n = 1, k = 6, target = 3 -> 1
n = 2, k = 6, target = 7 -> 6
n = 30, k = 30, target = 500 -> 222616187

*/

public class M_DP_1155 {

    public static int MODULO = (int) 1e9 + 7;

    // DP, recursive, memo
    // 6ms
    public int numRollsToTarget1(int n, int k, int target) {
        if (n * k < target || n * 1 > target) {
            return 0;
        }

        Integer[][] memo = new Integer[n + 1][target + 1];

        return count(n, target, k, memo);
    }

    private int count(int n, int target, int k, Integer[][] memo) {
        if (target == 0) {
            return n == 0 ? 1 : 0;
        }

        if (n <= 0) {
            return 0;
        }

        if (memo[n][target] != null) {
            return memo[n][target];
        }

        int count = 0;

        for (int i = 1; i <= k; i++) {
            if (i > target) {
                break;
            }

            count += count(n - 1, target - i, k, memo);
            count %= MODULO;
        }

        memo[n][target] = count;

        return memo[n][target];
    }

    // DFS
    // DP, iterative
    // 11ms
    public int numRollsToTarget2(int n, int k, int target) {
        int[][] counts = new int[n + 1][target + 1]; 
        counts[0][0] = 1;

        int MOD = (int) 1e9 + 7;

        for (int idx = 2; idx <= n; ++idx) {

            // minimal sum for i dice
            int minSum = idx * 1;
            // maximal sum for i dice
            int maxSum = Math.min(idx * k, target);

            for (int sum = minSum; sum <= maxSum; ++sum) {

                for (int face = 1; face <= k; ++face) {
                    if (sum >= face) {
                        counts[idx][sum] += counts[idx - 1][sum - face];
                        counts[idx][sum] %= MOD;
                    }
                }
            }
        }

        return counts[n][target];
    }

}
