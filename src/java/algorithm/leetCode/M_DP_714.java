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
   No

 ? max buy/sell per day
   max 1 buying OR selling, per day

 ? max buy/sell total
   no limit

 */

public class M_DP_714 {
    
    public int maxProfit0(int[] prices, int fee) {
        int L = prices.length;
        
        // max profit achievable by day i
        int[] maxProfits = new int[L];
        
        int lowestBuyPrice = prices[0];
        
        for (int i = 1; i < L; ++i) {
            int price = prices[i];
            
            int profit = price - lowestBuyPrice - fee;
            maxProfits[i] = Math.max(maxProfits[i - 1], profit);
            
            // Update lowest effective buy price
            // Can buy using yesterday's profit
            int effectiveBuyPrice = price - maxProfits[i - 1];
            lowestBuyPrice = Math.min(lowestBuyPrice, effectiveBuyPrice);
        }
        
        return maxProfits[L - 1];
    }
  
    public int maxProfit(int[] prices, int fee) {
        int L = prices.length;
        
        int[] maxProfitsIfBuy = new int[L];
        maxProfitsIfBuy[0] = -prices[0];

        int[] maxProfitsIfSell = new int[L];
        maxProfitsIfSell[0] = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            int profitIfBuy = maxProfitsIfSell[i - 1] - price;
            maxProfitsIfBuy[i] = Math.max(maxProfitsIfBuy[i - 1], profitIfBuy);
        
            int profitIfSell = maxProfitsIfBuy[i - 1] + price - fee;
            maxProfitsIfSell[i] = Math.max(maxProfitsIfSell[i - 1], profitIfSell);
        }

        return maxProfitsIfSell[L - 1];
    } 
}
