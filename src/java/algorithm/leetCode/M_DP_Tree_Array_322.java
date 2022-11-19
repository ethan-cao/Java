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
        System.out.println(coinChange0(new int[]{2}, 3));                      // -1
        System.out.println(coinChange0(new int[]{1, 2, 5}, 11));               // 3
        System.out.println(coinChange0(new int[]{1, 10, 2, 5}, 19));           // 4
        System.out.println(coinChange0(new int[]{2, 5, 10, 1}, 27));           // 4
        System.out.println(coinChange0(new int[]{7, 3, 11}, 36));              // 4
        System.out.println(coinChange0(new int[]{357, 239, 73, 52}, 9832));    // 35
        System.out.println(coinChange0(new int[]{186, 419, 83, 408}, 6249));   // 20
        System.out.println(coinChange0(new int[]{411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422}, 9864)); // 24
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // similar to 279
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        Arrays.sort(coins);

        int[] counts = new int[amount + 1];
        Arrays.fill(counts, Integer.MAX_VALUE);

        counts[0] = 0;

        for (int value = 1; value <= amount; ++value) {
            for (int i = 0; i < coins.length ; ++i) {
                
                if (coins[i] <= value && counts[value - coins[i]] != Integer.MAX_VALUE) {
                    int countWithCoin = 1 + counts[value - coins[i]];
                    int countWithoutCoin = counts[value];
                    counts[value] = Math.min(countWithoutCoin, countWithCoin);
                }
            }
        }

        return counts[amount] == Integer.MAX_VALUE ? -1 : counts[amount];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Unbounded Knapsack
    // DP, recursive, LTE
    public static int coinChange0(int[] coins, int amount) {
        return changeCoins(coins, 0, amount);
    }

    private static int changeCoins(int[] coins, int idx, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (idx >= coins.length) {
            return -1;
        }

        int coin = coins[idx];

        int changeWithCoin = -1;
        if (coin <= amount) {
            int change = changeCoins(coins, idx, amount - coin);

            if (change == -1) {
                changeWithCoin = -1;
            } else {
                changeWithCoin = 1 + change;
            }
        }

        int changeWithoutCoin = changeCoins(coins, idx + 1, amount);

        if (changeWithCoin == -1 && changeWithoutCoin == -1) {
            return -1;
        } else if (changeWithCoin == -1 || changeWithoutCoin == -1) {
            return changeWithCoin == -1 ? changeWithoutCoin : changeWithCoin;
        } else {
            return Math.min(changeWithCoin, changeWithoutCoin);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, recursive, memo
    // 70ms
    public static int coinChange1(int[] coins, int amount) {
        // use Integer[][] memo can simplify the code: https://leetcode.com/problems/coin-change/discuss/141064/Unbounded-Knapsack
        int[][] memo = new int[coins.length][amount + 1];
        for (int i = 0; i < coins.length; ++i) {
            for (int j = 1; j < amount + 1; ++j) {  // !!! from 1
                memo[i][j] = Integer.MAX_VALUE;
            }
        }

        return changeCoins(coins, 0, amount, memo);
    }

    private static int changeCoins(int[] coins, int idx, int amount, int[][] memo) {
        if (amount == 0) {
            return 0;
        }

        if (idx >= coins.length) {
            return -1;
        }

        if (memo[idx][amount] != Integer.MAX_VALUE) {
            return memo[idx][amount];
        }

        int coin = coins[idx];

        int changeWithCoin = -1;
        if (coin <= amount) {
            int change = changeCoins(coins, idx, amount - coin, memo);

            if (change == -1) {
                changeWithCoin = -1;
            } else {
                changeWithCoin = 1 + change;
            }
        }

        int changeWithoutCoin = changeCoins(coins, idx + 1, amount, memo);

        if (changeWithCoin == -1 && changeWithoutCoin == -1) {
            memo[idx][amount] = -1;
        } else if (changeWithCoin == -1 || changeWithoutCoin == -1) {
            memo[idx][amount] = changeWithCoin == -1 ? changeWithoutCoin : changeWithCoin;
        } else {
            memo[idx][amount] = Math.min(changeWithCoin, changeWithoutCoin);
        }

        return memo[idx][amount];
    }

    // DP iterative
    // 20ms
    public static int coinChange3(int[] coins, int amount) {
        // changes[i]: minimal number of coins needed to make up to amount i with all coins, we need changes[amount]
        // base changes[0] = 0
        int[] changes = new int[amount + 1];

        // in order to know changes[amount], we first need to know changes[amount-1], changes[amount-2] ... changes[1], changes[0] = 0
        // so sub problem is to get changes[i], i starts from 1 until amount
        // changes[i] should be 1 + existing solution if it exists
        for (int i = 1; i <= amount; ++i) {
            int minChange = Integer.MAX_VALUE;    // could also be amount + 1

            for (int coin : coins) {
                // given amount i, check coins that are possible to use: smaller than i
                // if just use 1 of that coin, how many coins needed to make up the remaining amount i - coin

                // changes[i - coin * 1] is either a positive number or -1
                // if changes[i-coin] is a positive number, then to make up to i, just need 1 more coin
                // if changes[i-coin] is -1, then not possible to make up to i - coin and not possible to make up to i,
                // since solution for changes[i] must be built on previous solutions

                // Not necessary to use multiple that coin, since that case has already been checked
                // for instance coins [3] and amount 10,
                // 10-3=7, no solution for using 1 coin 3 to get 7
                // 10-3*2=4, this is actually 7-3=4, which already checked
                if (coin > i) {
                    continue;
                }

                // if sort coins, we can terminate early, if (coin > i) break; (it costs more time)

                if (changes[i - coin] != -1) {
                    int change = changes[i - coin] + 1;
                    minChange = Math.min(minChange, change);
                }
            }

            changes[i] = minChange == Integer.MAX_VALUE ? -1 : minChange;
        }

        return changes[amount];
    }

    //DP recursive
    // 39ms
    public static int coinChange2(int[] coins, int amount) {
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

    public static int coinChange4(int[] coins, int amount) {
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

    // BFS
    private static int coinChange5(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        Arrays.sort(coins);

        Queue<Integer> queue = new LinkedList<>();
        boolean[] set = new boolean[amount + 1];

        queue.offer(amount);
        set[amount] = true;

        int currLevel = 1;
        int currLevelCount = 1;
        int nextLevelCount = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            currLevelCount--;

            for (int coin : coins) {
                int child = curr - coin;
                if (child == 0) {
                    return currLevel;
                } else if (child > 0 && !set[child]) {
                    queue.add(child);
                    set[child] = true;
                    nextLevelCount++;
                } else if (child < 0) {
                    continue;
                }
            }

            if (currLevelCount == 0) {
                currLevel++;
                currLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }

        return -1;
    }

}