package algorithm.leetCode;

/*
Say you have an array for which the ith element is the price of a given stock on day i.
You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times) 
with the following restrictions:
You may not engage in multiple transactions at the same time (must sell the stock before you buy again)
After you sell your stock, you cannot buy stock on next day. (cooldown 1 day)

### Example
[1,2,3,0,2] -> 3
transactions = [buy, sell, cooldown, buy, sell]

# Analysis
 ? buy/sell possible on the same day
   buying and selling on the same day is implictly not allowed, since
    1. no profit
    2. doing so, disallow to buy on the next day

 ? max buy/sell total
   no limit

*/

public class M_DP_Array_309 {

    // --------------------------------------------------------------------------
    // ✅  DP, iterative, 0ms
    // Time: O(N), Space: O(N)
    public static int maxProfit1(int[] prices) {
        int L = prices.length;
        
        // max profit until day i, with the last action as buy 
        // prefer to use state in name: Hold is better than Buy
        int[] maxProfitHold = new int[L];
        maxProfitHold[0] = -prices[0];

        // max profit until day i, with the last action as sell
        int[] maxProfitSold = new int[L];
        maxProfitSold[0] = 0;
    
        if (L == 1) {
            return maxProfitSold[L - 1];
        } 

        int profitHold = - prices[1];
        maxProfitHold[1] = Math.max(maxProfitHold[0], profitHold);

        // implictly not allow to buy and sell on the same day, since
        // 1. no profit
        // 2. doing so, disallow to buy on the next day
        int profitSold = + prices[1] + maxProfitHold[0];
        maxProfitSold[1] = Math.max(maxProfitSold[0], profitSold);
    
        for (int i = 2; i < L; ++i) {
            int price = prices[i];

            profitHold = - price + maxProfitSold[i - 2];
            maxProfitHold[i] = Math.max(maxProfitHold[i - 1], profitHold);

            profitSold = + price + maxProfitHold[i - 1];
            maxProfitSold[i] = Math.max(maxProfitSold[i - 1], profitSold);

        }
    
        return maxProfitSold[L - 1];
    }

    // -------------------------------------------------------------------------------------------------
    // ✅ DP, 0ms
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

    // --------------------------------------------------------------------------
    public int maxProfit0(int[] prices) {
        int L = prices.length;
        
        // max profit when sell on day i
        int[] maxProfitSold = new int[L];
        maxProfitSold[0] = 0;  

        // max profit when rest on day i
        int[] maxProfitsRest = new int[L];
        maxProfitsRest[0] = 0;  
        
        int lowestCost = prices[0];
        
        for (int i = 1; i < L; ++i) {
            int price = prices[i];
            
            // effective cost if I buy today
            int effectiveCostToday = price - maxProfitsRest[i - 1];

            // find the lowest cost, basically we consider buy on anyday till today
            lowestCost = Math.min(lowestCost, effectiveCost);
            
            // Sell today, use lowestBuyPrice from previous days
            maxProfitSold[i] = price - lowestCost;
            
            // Rest today: max(keep resting from yesterday, cooldown from yesterday's sell)
            maxProfitsRest[i] = Math.max(maxProfitsRest[i - 1], maxProfitSold[i - 1]);
        }
        
        return Math.max(maxProfitSold[L - 1], maxProfitsRest[L - 1]);
    }
}
