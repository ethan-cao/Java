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

coins is not sorted

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
        // changes[i] : number of coins used to make up to amount i, we need changes[amount]
        int[] changes = new int[amount + 1];

        // in order to know changes[amount], we first need to know changes[amount-1], changes[amount-2] ... changes[1], changes[0] = 0
        // so sub problem is to get changes[i], i starts from 1 until amount
        // changes[i] should be 1 + existing solution if it exists
        for (int i = 1; i <= amount; ++i) {
            int minChanges = Integer.MAX_VALUE;    // could also be amount + 1

            for (int coin : coins) {
                // given amount i, check coins that are possible to use: smaller than i
                // if just use 1 of that coin, how many coins needed to make up the remain amount i - coin
                // Not necessary to use multiple that coin,
                // changes[i - coin * 1] is either a positive number or -1
                // if changes[i-coin] is a positive number, then to make up to i, just need 1 more coin
                // if changes[i-coin] is -1, then not possible to make up to i - coin and not possible to make up to i
                // since solution for changes[i] must be built on previous solutions

                if (coin <= i && changes[i - coin] != -1) {
                    minChanges = Math.min(minChanges, changes[i - coin]);
                }
            }

            changes[i] = minChanges == Integer.MAX_VALUE ? -1 : minChanges + 1;
        }

        return changes[amount];
    }

    //DP recursive
    public static int coinChange1(int[] coins, int amount) {
        return changeCoin(coins, amount, new int[amount + 1]);
    }

    private static int changeCoin(int[] coins, int amount, int[] changes) {
        if (changes[amount] != 0) {
            return changes[amount];
        }

        for (int coin : coins) {
            int change = changeCoin(coins, amount - coin, changes);


            changes[amount] = Math.min(changes[amount], change + 1);
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
}
