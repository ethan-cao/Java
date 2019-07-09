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
        System.out.println(coinChange(coins3, 19)); // 10

        int[] coins4 = {186,419,83,408};
        System.out.println(coinChange(coins4, 6249)); // 20
    }

    public static int coinChange(int[] coins, int amount) {
        int minNumberOfCoin = 0;
        int required = amount;

        Arrays.sort(coins);

        int start = coins.length - 1;
        while (start >= 0) {

            int index = start;
            while (index >= 0 && amount > 0) {
                int coinNumber = amount / coins[index];
                minNumberOfCoin += coinNumber;
                amount = amount - coinNumber * coins[index];
                index--;
            }

            if (amount == 0) {
                return minNumberOfCoin;
            }

            start--;
            amount = required;
            minNumberOfCoin = 0;
        }

        return amount == 0 ? minNumberOfCoin : -1;
    }


}
