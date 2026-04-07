package algorithm.leetCode;

/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
Find the maximum profit you can achieve. You may complete at most k transactions.
You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

0 <= k <= 100
0 <= prices.length <= 1000
0 <= prices[i] <= 1000

#Example
k = 2, prices = [2,4,1] -> 2
k = 2, prices = [3,2,6,5,0,3] -> 7


# ASK
 ? buy/sell possible on the same day
   yes

 ? max buy/sell per day
   no limit on buying and then selling per day

 ? max buy/sell total
   max k full cycle of buying and then selling, in total

*/

public class M_DP_188 {

    //----------------------------------------------------------------------------------------------
    // ✅  DP 
    public int maxProfit(int k, int[] prices) {
        int L = prices.length;

        int[][] maxProfitHold = new int[k][L];
        int[][] maxProfitSold = new int[k][L];

        for (int i = 0; i < k; i++) {
            maxProfitHold[i][0] = -prices[0];
            maxProfitSold[i][0] = 0;
        }

        for (int j = 0; j < k; j++) {
            for (int i = 1; i < L; ++i) {

                int price = prices[i];

                int profitSold = + price + maxProfitHold[j][i - 1];
                maxProfitSold[j][i] = Math.max(maxProfitSold[j][i - 1], profitSold);

                int prevSold = (j == 0) ? 0 : maxProfitSold[j - 1][i - 1];
                int profitHold = - price + prevSold;
                maxProfitHold[j][i] = Math.max(maxProfitHold[j][i - 1], profitHold);
            }
        }

        return maxProfitSold[k - 1][L - 1];
    }
    
    //----------------------------------------------------------------------------------------------
    // DP
    // Tim: O(N^2), 2ms
    public int maxProfit1(int k, int[] prices) {
        int L = prices.length;

        // maxProfits[i][j]: maximum profit achievable with at most i transactions up to day j.
        int[][] maxProfits = new int[k + 1][L];

        for (int i = 1; i <= k; ++i) {
            // BASE
            int lowestBuyPrice = prices[0];
            maxProfits[i][0] = 0;

            for (int j = 1; j < L; ++j) {
                int price = prices[j];
            
                // effective buy price considers the actual price minus the profit made by the previous transaction up to the previous day.
                // representing the adjusted cost if one were to have bought the stock on the current day considering the profits from previous transactions.
                // the reason use max profit up to the previous day is to ensure that each transaction is completed before starting another
                // the profit from selling a stock on a given day must only consider profits from buys that could have been completed on previous days.
                int effectiveBuyPrice = price - maxProfits[i - 1][j - 1];
                lowestBuyPrice = Math.min(lowestBuyPrice, effectiveBuyPrice);
                
                int profitSell = price - lowestBuyPrice;
                maxProfits[i][j] = Math.max(maxProfits[i][j - 1], profitSell);
            }
        }

        return maxProfits[k][L - 1];
    }

}
