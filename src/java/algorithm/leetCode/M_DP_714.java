package algorithm.leetCode;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day
and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. 
You may complete as many transactions as you like, 
but you need to pay the transaction fee for each transaction.

Note:
You may not engage in multiple transactions simultaneously 
(i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale. 
 

# ASK
 ? buy/sell possible on the same day
   buying and selling on the same day is implictly not allowed, since
    1. no profit
    2. doing so, disallow to buy on the next day

 ? max buy/sell total
   no limit

 */

public class M_DP_714 {
   
    // --------------------------------------------------------------------------
    // ✅  DP, iterative
    public int maxProfit(int[] prices, int fee) {
        int  L = prices.length; 
        
        int[] maxProfitHold = new int[L];
        maxProfitHold[0] = -prices[0];

        int[] maxProfitSold = new int[L];
        maxProfitSold[0] = 0;
    
        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            int profitHold = - price + maxProfitSold[i - 1];
            maxProfitHold[i] = Math.max(maxProfitHold[i - 1], profitHold);
        
            int profitSold = + price + maxProfitHold[i - 1] - fee;
            maxProfitSold[i] = Math.max(maxProfitSold[i - 1], profitSold);
        }

        return maxProfitSold[L - 1];
    }  
    public int maxProfit0(int[] prices, int fee) {
        int L = prices.length;
        
        // max profit achievable by day i
        int[] maxProfits = new int[L];
        
        int lowestBuyPrice = prices[0];
        
        for (int i = 1; i < L; ++i) {
            int price = prices[i];
            
            // Update lowest effective buy price
            // Can buy using yesterday's profit
            int effectiveBuyPrice = price - maxProfits[i - 1];
            lowestBuyPrice = Math.min(lowestBuyPrice, effectiveBuyPrice);

            int profit = price - lowestBuyPrice - fee;
            maxProfits[i] = Math.max(maxProfits[i - 1], profit);
        }
        
        return maxProfits[L - 1];
    }
  

}
