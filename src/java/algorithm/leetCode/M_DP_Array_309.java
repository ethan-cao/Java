package algorithm.leetCode;

/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit.You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:
You may not engage in multiple transactions at the same time (must sell the stock before you buy again)
After you sell your stock, you cannot buy stock on next day. (cooldown 1 day)

### Example
[1,2,3,0,2] -> 3
transactions = [buy, sell, cooldown, buy, sell]

*/

public class M_DP_Array_309 {

    public static void main(String... args) {
    }

    // DP, State Machine, 0ms
    // Time: O(N), Space: O(1)
    public int maxProfit(int[] prices) {
        final int L = prices.length;

        // 1st day
        int balanceWithCoolDown = 0;
        int balanceWithStock = 0 - prices[0]; // buy stock with 1st price
        int balanceWithoutStock = 0;

        // since 2nd day
        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            int previousBalanceWithCoolDown = balanceWithCoolDown;
            int previousBalanceWithStock = balanceWithStock;

            // 2 possible ways to enter cooldown, cooldown or just sold
            balanceWithCoolDown = Math.max(balanceWithCoolDown, balanceWithoutStock);

            // 2 possible ways to enter with stock, with stock before or just bought
            balanceWithStock = Math.max(balanceWithStock, previousBalanceWithCoolDown - price);

            // 2 possible ways to enter without stock, without stock before or just sold
            balanceWithoutStock = Math.max(balanceWithoutStock, previousBalanceWithStock + price);
        }

        return Math.max(balanceWithCoolDown, balanceWithoutStock);
    }

    // DP, iterative, 0ms
    // Time: O(N), Space: O(N)
    public static int maxProfit1(int[] prices) {
        if (null == prices || prices.length < 2) {
            return 0;
        }

        final int L = prices.length;

        // buys[i]: max profit made from day 0 to day i, with the last action as buy or
        // not buy
        int[] buys = new int[L];
        // sells[i]: max profit made from day 0 to day i, with the last action as sell
        // or not sell
        int[] sells = new int[L];

        buys[0] = -prices[0];
        buys[1] = Math.max(buys[0], -prices[1]);
        sells[0] = 0;
        sells[1] = Math.max(sells[0], buys[0] + prices[1]); // must sell after buy, so buy[0] must be -price[0]

        for (int i = 2; i < L; ++i) {
            buys[i] = Math.max(buys[i - 1], sells[i - 2] - prices[i]);
            sells[i] = Math.max(sells[i - 1], buys[i - 1] + prices[i]);
        }

        return Math.max(buys[L - 1], sells[L - 1]);
    }

}
