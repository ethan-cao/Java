package algorithm.leetCode;

/*
You are given coins of different denominations and a total amount of money amount.

Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

### Example
Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Input: coins = [2], amount = 3
Output: -1

*/


import java.util.Arrays;

public class M_DP_Array_322 {

    public static void main(String... args) {
        int[] coins1 = {1, 2, 5};
        System.out.println(coinChange(coins1, 11)); // 3

        int[] coins2 = {2};
        System.out.println(coinChange(coins2, 3)); // -1

        int[] coins3 = {1, 10, 2, 5};
        System.out.println(coinChange(coins3, 19)); // 4

        int[] coins4 = {7, 3, 11};
        System.out.println(coinChange(coins4, 36)); // 4

        int[] coins9 = {186, 419, 83, 408};
        System.out.println(coinChange(coins9, 6249)); // 20
    }

    //DP iterative
    public static int coinChange(int[] coins, int amount) {
        // changes[i] :  number of coins used to make up to i, we need changes[amount]
        int[] changes = new int[amount + 1];

        for (int i = 1; i <= amount; ++i) {
            int minChanges = Integer.MAX_VALUE;

            for (int coin : coins) {
                // each iteration checks, with coin, how many changes it needs to make up to i

                if (i - coin >= 0 && changes[i - coin] != -1) {
                    minChanges = Math.min(minChanges, changes[i - coin]);
                }
            }

            changes[i] = minChanges == Integer.MAX_VALUE ? -1 : minChanges + 1;

            // 1,-1,-1,-1,-1
            //
        }

        return changes[amount];
    }

    public static int coinChange11(int[] coins, int amount) {
        int minNumberOfCoin = -1;

        Arrays.sort(coins);

        int[] changes = new int[coins.length];

        for (int i = coins.length - 1; i >= 0; --i) {
            int numberOfCoin = -1;
            changes[i] = amount / coins[i];
            int remain = 0;

            for (int j = changes[i]; j >= 0; --j) {
                remain = amount - j * coins[i];

                int k = i - 1;
                while (remain != 0 && k >= 0) {
                    changes[k] = remain / coins[k];
                    remain = remain % coins[k];
                    k--;
                }

                if (remain == 0) {
                    numberOfCoin = Arrays.stream(changes).sum();
                    break;
                }
            }

            if (minNumberOfCoin == -1 && remain == 0) {
                minNumberOfCoin = numberOfCoin;
            }

            if (remain == 0) {
                minNumberOfCoin = Math.min(numberOfCoin, minNumberOfCoin);
            }

            Arrays.fill(changes, 0);
        }

        return minNumberOfCoin;
    }

    //DP recursive
    public static int coinChange1(int[] coins, int amount) {
        int minNumberOfCoin = -1;

        return minNumberOfCoin;
    }
}
