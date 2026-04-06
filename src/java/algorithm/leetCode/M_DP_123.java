package algorithm.leetCode;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. 
You may complete at most two transactions.
You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

1 <= prices.length <= 105
0 <= prices[i] <= 105

#Example
[3,3,5,0,0,3,1,4] -> 6
[1,2,3,4,5] -> 4
[7,6,4,3,1] -> 0


# ASK
 ? buy/sell possible on the same day
   buying and selling on the same day is implictly not allowed, since
    1. no profit
    2. doing so, disallow to buy on the next day

 ? max buy/sell total
   max 2 full cycle of buying and then selling, in total

*/

public class M_DP_123 {

    //----------------------------------------------------------------------------------------------
    // ✅  DP, 2ms 
    public int maxProfit1(int[] prices) {
        final int L = prices.length;

        int maxProfitHold1 = -prices[0];
        int maxProfitSold1 = 0;
    
        int maxProfitHold2 = -prices[0];
        int maxProfitSold2 = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            int profitSold1 = + price + maxProfitHold1;
            maxProfitSold1 = Math.max(maxProfitSold1, profitSold1);
            
            int profitHold1 = -price;
            maxProfitHold1 = Math.max(maxProfitHold1, profitHold1);

            int profitSold2 = + price + maxProfitHold2;
            maxProfitSold2 = Math.max(maxProfitSold2, profitSold2);

            int profitHold2 = - price + maxProfitSold1;
            maxProfitHold2 = Math.max(maxProfitHold2, profitHold2);
        }

        return maxProfitSold2; 
    }

    //----------------------------------------------------------------------------------------------
    public int maxProfit00(int[] prices) {
        final int L = prices.length;
        if (L == 0) return 0;
        
        int lowestBuyPrice1 = prices[0];
        int maxProfitSell1 = 0;
        
        int lowestBuyPrice2 = prices[0];
        int maxProfitSell2 = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];
            
            // 1st transaction, buy/sell allowed on the same day
            lowestBuyPrice1 = Math.min(lowestBuyPrice1, price);
            int profit1 = price - lowestBuyPrice1;
            maxProfitSell1 = Math.max(maxProfitSell1, profit1);
            
            // 2nd transaction (effective buy price accounts for profit from 1st transaction)
            int effectiveBuyPrice2 = price - maxProfitSell1;
            lowestBuyPrice2 = Math.min(lowestBuyPrice2, effectiveBuyPrice2);
            int profit2 = price - lowestBuyPrice2;
            maxProfitSell2 = Math.max(maxProfitSell2, profit2);
        }

        return maxProfitSell2;
    }

 
    //----------------------------------------------------------------------------------------------
    // DP
    // Time: O(N), 5ms
    public int maxProfit(int[] prices) {
        final int L = prices.length;

        int profit1 = 0;
        int lowestPrice1 = prices[0];

        int profit2 = 0;
        int lowestPrice2 = prices[0];

        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            int profitIfNoAction1 = profit1;
            int profitIfSelling1 = price - lowestPrice1;
            profit1 = Math.max(profitIfNoAction1, profitIfSelling1);
            lowestPrice1 = Math.min(lowestPrice1, price);

            int profitIfNoAction2 = profit2;
            int profitIfSelling2 = price - lowestPrice2;
            profit2 = Math.max(profitIfNoAction2, profitIfSelling2);
            // !!! price - profit1: profit from 1st transaction should be taken into lowest price consideration
            lowestPrice2 = Math.min(lowestPrice2, price - profit1); 
        }

        return profit2;
    }
}
