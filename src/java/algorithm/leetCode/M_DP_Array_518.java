package algorithm.leetCode;

/*
You are given coins of different denominations and a total amount of money.
Write a function to compute the number of combinations that make up that amount.
You may assume that you have infinite number of each kind of coin.

0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer

### Example
amount = 3, coins = [2] -> 0
amount = 10, coins = [10] -> 1
amount = 5, coins = [1, 2, 5] -> 4
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

### keys
* This question asks for combination, the order does not matters
* you can reuse each number

*/

import java.util.Arrays;

public class M_DP_Array_518 {

    public static void main(String... args) {
        System.out.println(change1(4, new int[] { 1, 2, 3 })); // 4
        System.out.println(change1(0, new int[] { 7 })); // 1
        System.out.println(change1(5, new int[] { 1, 2, 5 })); // 4
        System.out.println(change1(3, new int[] { 2 })); // 0
        System.out.println(change1(10, new int[] { 10 })); // 1
        System.out.println(change1(500, new int[] { 3, 5, 7, 8, 9, 10, 11 })); // 35502874
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP
    // Time: O(N^2)
    public int change000(int amount, int[] coins) {
        int L = coins.length;
        int[][] counts = new int[L + 1][amount + 1];

        for (int i = 1; i <= L; ++i) {
            int coin = coins[i- 1];

            // BASE
            counts[i][0] = 1;

            for (int value = 1; value <= amount; ++value) {
                // TRANSFORM
                // skip(i) = changes(i-1, amount)
                // take(i) = changes(i, amount - coins[i]), if coins[i] <= amount
                // changes(i, j) = take + skip

                // existing combination, with all previously used coins
                int skip = counts[i - 1][value];

                // a new combination, with the new coin
                int take = value >= coin ? counts[i][value - coin] : 0;

                counts[i][value] = take + skip;
            }
        }

        return counts[L][amount];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP
    // Time: O(N^2)
    public int change123(int amount, int[] coins) {
        final int L = coins.length;
        int[] counts = new int[amount + 1];

        counts[0] = 1;

        // TRANSFORM
        // skip(i) = changes(i-1, amount)
        // take(i) = changes(i, amount - coins[i]),  coins[i] <= amount
        // changes(i, j) = take + skip
        for (int i = 0; i < L; ++i) {
            for (int value = 1; value <= amount; ++value) {
                int skip = i == 0 ? 0 : counts[value];
                int take = value - coins[i] >= 0 ? counts[value - coins[i]] : 0;

                counts[value] = take + skip;
            }
        }

        return counts[amount];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, recursive, top-down
    // 14ms
    public int change2(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }

        Arrays.sort(coins);

        if (coins[0] > amount) {
            return 0;
        }

        Integer[][] memo = new Integer[coins.length][amount + 1];

        // when start from the end, it's top-down DP
        return getChange1(coins, coins.length - 1, amount, memo);
    }

    private int getChange1(int[] coins, int idx, int amount, Integer[][] memo) {
        // BASE
        // changes(i, 0) = 1
        if (amount == 0) {
            return 1;
        }

        if (idx < 0) {
            return 0;
        }

        if (memo[idx][amount] != null) {
            return memo[idx][amount];
        }

        // TRANSFORM
        // skip(i) = changes(i-1, amount)
        // take(i) = changes(i, amount - coins[i]),  coins[i] <= amount
        // changes(i, j) = take + skip
        int take = coins[idx] <= amount ? getChange(coins, idx, amount - coins[idx], memo) : 0;
        int skip = getChange(coins, idx - 1, amount, memo);

        memo[idx][amount] = take + skip;

        return memo[idx][amount];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int change(int amount, int[] coins) {
        int[][] counts = new int[amount + 1][coins.length];
        Arrays.fill(counts[0], 1);

        for (int value = 1; value <= amount; ++value) {
            for (int i = 0; i < coins.length; ++i) {

                int coin = coins[i];

                int skip = i >= 1 ? counts[value][i - 1] : 0;
                int take = value >= coin ? counts[value - coin][i] : 0;

                counts[value][i] = skip + take;
            }
        }

        return counts[amount][coins.length - 1];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative, bottom-up
    // 6ms
    public static int change3(int amount, int[] coins) {
        // BASE
        // changes(i, 0) = 1
        if (amount == 0) {
            return 1;
        }

        Arrays.sort(coins);

        if (coins[0] > amount) {
            return 0;
        }

        final int L = coins.length;
        int[][] memo = new int[L][amount + 1];

        for (int i = 0; i < L; ++i) {
            memo[i][0] = 1;
        }

        // TRANSFORM
        // skip(i) = changes(i-1, amount)
        // take(i) = changes(i, amount - coins[i]),  coins[i] <= amount
        // changes(i, j) = take + skip
        for (int i = 0; i < L; ++i) {
            for (int target = 1; target <= amount; ++target) {

                int skip = i == 0 ? 0 : memo[i - 1][target];
                int take = target - coins[i] >= 0 ? memo[i][target - coins[i]] : 0;

                memo[i][target] = take + skip;
            }
        }

        return memo[L - 1][amount];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative, bottom-up, condensed space
    // 3ms
    public static int change4(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }

        Arrays.sort(coins);

        if (coins[0] > amount) {
            return 0;
        }

        final int L = coins.length;
        int[] memo = new int[amount + 1];
        memo[0] = 1;

        for (int i = 0; i < L; ++i) {
            for (int target = 1; target <= amount; ++target) {

                int skip = i == 0 ? 0 : memo[target];
                int take = target - coins[i] >= 0 ? memo[target - coins[i]] : 0;

                memo[target] = take + skip;
            }
        }

        return memo[amount];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative, bottom-up
    public int change111(int amount, int[] coins) {
        int[][] counts = new int[amount + 1][coins.length];
        Arrays.fill(counts[0], 1);

        for (int value = 1; value <= amount; ++value) {
            for (int i = 0; i < coins.length; ++i) {

                int coin = coins[i];

                int skip = i >= 1 ? counts[value][i - 1] : 0;
                int take = value >= coin ? counts[value - coin][i] : 0;

                counts[value][i] = skip + take;
            }
        }

        return counts[amount][coins.length - 1];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS, brute force
    // Time: O(2^n) 99ms
    public static int change1(int amount, int[] coins) {
        Integer[][] memo = new Integer[coins.length][amount + 1];

        // when start from the start, it's DFS
        return getChange(coins, 0, amount, memo);
    }

    private static int getChange(int[] coins, int idx, int amount, Integer[][] memo) {
        if (amount == 0) {
            return 1;
        }

        if (idx >= coins.length) {
            return 0;
        }

        if (memo[idx][amount] != null) {
            return memo[idx][amount];
        }

        memo[idx][amount] = 0;

        // !!! combination, starts from idx
        for (int i = idx; i < coins.length; ++i) {
            if (coins[i] <= amount) {
                memo[idx][amount] += getChange(coins, i, amount - coins[i], memo);
            }
        }

        return memo[idx][amount];
    }
}