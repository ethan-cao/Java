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

    // DP, State Machine, 0ms
    // Time: O(N), Space: O(1)
    public int maxProfit(int[] prices) {
        final int L = prices.length;

        // BASE, 1st day
        int profitIfBuy = 0 - prices[0];
        int profitIfSell = 0;
        int profitIfCooldown = 0;

        // since 2nd day
        for (int i = 1; i < L; ++i) {
            int price  = prices[i];
        
            int previousProfitIfBuy = profitIfBuy;
            int previousProfitIfSell = profitIfSell;
        
            // 2 possible ways to enter buy, with stock before or just bought
            profitIfBuy = Math.max(profitIfBuy, profitIfCooldown - price);
            // 2 possible ways to enter sell, without stock before or just sold
            profitIfSell = Math.max(profitIfSell, previousProfitIfBuy + price);
            // 2 possible ways to enter cooldown, cooldown or just sold
            profitIfCooldown = Math.max(profitIfCooldown, previousProfitIfSell);

            profit = Math.max(profitIfCooldown, Math.max(profitIfBuy, profitIfSell));
        }

        return profit;
    }

    // DP, iterative, 0ms
    // Time: O(N), Space: O(N)
    public static int maxProfit1(int[] prices) {
        final int L = prices.length;

        // buys[i]: max profit made from day 0 to day i, with the last action as buy or not buy
        int[] profitIfBuying = new int[L];
        // sells[i]: max profit made from day 0 to day i, with the last action as sell or not sell
        int[] profitIfSelling = new int[L];

        profitIfBuying[0] = -prices[0];
        profitIfBuying[1] = Math.max(profitIfBuying[0], -prices[1]);
        profitIfSelling[0] = 0;
        profitIfSelling[1] = Math.max(profitIfSelling[0], profitIfBuying[0] + prices[1]); // must sell after buy, so buy[0] must be -price[0]

        for (int i = 2; i < L; ++i) {
            profitIfBuying[i] = Math.max(profitIfBuying[i - 1], profitIfSelling[i - 2] - prices[i]);
            profitIfSelling[i] = Math.max(profitIfSelling[i - 1], profitIfBuying[i - 1] + prices[i]);
        }

        return Math.max(profitIfBuying[L - 1], profitIfSelling[L - 1]);
    }

}
