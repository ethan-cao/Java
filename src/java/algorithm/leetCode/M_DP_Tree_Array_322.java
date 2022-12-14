package algorithm.leetCode;

/*
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

1 <= coins.length <= 12
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10^4

### Example
coins = [1, 2, 5], amount = 11 -> 3, 11 = 5 + 5 + 1
coins = [2], amount = 3 -> -1

*/

import java.util.*;

public class M_DP_Tree_Array_322 {

    public static void main(String... args) {
        int[] coins1 = {1, 2, 5};
        System.out.println(coinChange2(coins1, 11)); // 3

        int[] coins2 = {2};
        System.out.println(coinChange2(coins2, 3)); // -1

        int[] coins3 = {1, 10, 2, 5};
        System.out.println(coinChange2(coins3, 19)); // 4

        int[] coins4 = {7, 3, 11};
        System.out.println(coinChange2(coins4, 36)); // 4

        int[] coins9 = {186, 419, 83, 408};
        System.out.println(coinChange2(coins9, 6249)); // 20
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
                // changes[i - coin * 1] is either a positive number or -1
                // if changes[i-coin] is a positive number, then to make up to i, just need 1 more coin
                // if changes[i-coin] is -1, then not possible to make up to i - coin and not possible to make up to i
                // since solution for changes[i] must be built on previous solutions

                // Not necessary to use multiple that coin, since that case has already been checked
                // for instance coins [3] and amount 10,
                // 10-3=7, no solution for using 1 coin 3 to get 7
                // 10-3*2=4, this is actually 7-3=4, which already checked

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
        int[] changes = new int[amount + 1];
        Arrays.fill(changes, Integer.MAX_VALUE);
        changes[0] = 0;

        return changeCoin(coins, amount, changes);
    }

    private static int changeCoin(int[] coins, int amount, int[] changes) {
        if (changes[amount] != Integer.MAX_VALUE) {
            return changes[amount];
        }

        for (int coin : coins) {
            if (amount >= coin) {
                int change = changeCoin(coins, amount - coin, changes);

                if (change >= 0) {
                    // compare and increase only when change >= 0
                    changes[amount] = Math.min(changes[amount], change + 1);
                }
            }
        }

        changes[amount] = changes[amount] == Integer.MAX_VALUE ? -1 : changes[amount];
        return changes[amount];
    }

    // Greedy, use larger coin as much as possible
    private static int minChange = Integer.MAX_VALUE;

    public static int coinChange2(int[] coins, int amount) {
        Arrays.sort(coins);
        minChange = Integer.MAX_VALUE;

        changeCoin(coins, amount, coins.length - 1, 0);

        return minChange == Integer.MAX_VALUE ? -1 : minChange;
    }

    private static void changeCoin(int[] coins, int amount, int coinIdx, int change) {
        // this must be the first return condition, always update minChange
        if (amount == 0) {
            minChange = Math.min(change, minChange);
            return;
        }

        if (coinIdx < 0) {
            return;
        }

        int coin = coins[coinIdx];

        // change + i < minChange is to avoid not necessary calculation
        for (int i = amount / coin; i >= 0 && change + i < minChange; --i) {
            // try smaller coin, coins[coinIdx-1] <= coins[coinIdx]
            changeCoin(coins, amount - i * coin, coinIdx - 1, change + i);
        }
    }

}