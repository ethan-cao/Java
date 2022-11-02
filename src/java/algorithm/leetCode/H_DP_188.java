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

# Analysis
Buy and sell cannot happen on the same day

max profit with at most k transaction

*/

public class M_DP_188 {

    // DP
    // Tim: O(N^2), 2ms
    public int maxProfit(int k, int[] prices) {
        final int L = prices.length;

        // profits[i][j]: balance with at most i transaction within j days
        int[][] profits = new int[k + 1][L];
    
        for (int t = 1; t <= k; ++t) {
          int lowestPrice = prices[0];
    
          for (int d = 1; d < L; ++d) {
            int price = prices[d];
    
            int profitIfNoAction = profits[t][d - 1];
    
            lowestPrice = Math.min(lowestPrice, price - profits[t - 1][d - 1]);
            int profitIfSell = price - lowestPrice;
    
            profits[t][d] = Math.max(profitIfNoAction, profitIfSell);
    
          }
        }
    
        return profits[k][L - 1];
    }

}
