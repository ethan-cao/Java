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

# Analysis
Buy and sell CANNOT happen on the same day

*/

public class M_DP_Array_309 {

    // DP, State Machine, 0ms
    // Time: O(N), Space: O(1)
    public int maxProfit(int[] prices) {
        final int L = prices.length;

        int profit = 0;

        // BASE, 1st day
        // 3 possible actions 
        int profitIfBuy = 0 - prices[0];
        int profitIfSell = 0;
        int profitIfCooldown = 0;

        // since 2nd day
        for (int i = 1; i < L; ++i) {
            int price  = prices[i];

            // !!! CANNOT buy and sell on the same day
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
        int L = prices.length;
        
        // max profit until day i, with the last action as buy 
        int[] maxProfitIfBuy = new int[L];
        // max profit until day i, with the last action as sell
        int[] maxProfitIfSell = new int[L];
    
        maxProfitIfBuy[0] = -prices[0];
        maxProfitIfSell[0] = 0;
    
        if (L == 1) {
            return Math.max(maxProfitIfBuy[L - 1], maxProfitIfSell[L - 1]);
        }
    
        // profit on that day if buy
        int profitIfBuy = -prices[1];
        maxProfitIfBuy[1] = Math.max(maxProfitIfBuy[0], profitIfBuy);

        // profit on that day if sell
        int profitIfSell = maxProfitIfBuy[0] + prices[1];
        maxProfitIfSell[1] = Math.max(maxProfitIfSell[0], profitIfSell);

        for (int i = 2; i < L; ++i) {
            int price = prices[i];

            profitIfBuy = maxProfitIfSell[i - 2] - price;
            maxProfitIfBuy[i] = Math.max(maxProfitIfBuy[i - 1], profitIfBuy);

            profitIfSell = maxProfitIfBuy[i - 1] + price;
            maxProfitIfSell[i] = Math.max(maxProfitIfSell[i - 1], profitIfSell);
        }
    
        return Math.max(maxProfitIfBuy[L - 1], maxProfitIfSell[L - 1]);
    }

}
