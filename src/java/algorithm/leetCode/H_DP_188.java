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

*/

public class M_DP_188 {

    public static void main(String... args) {
    }

    // DP
    // Tim: O(N^2), 2ms
    public int maxProfit(int k, int[] prices) {
        final int L = prices.length;

        if (L == 0 || k == 0) {
            return 0;
        }

        // balance[i][j]: balance with at most i transaction within j days
        int[][] balance = new int[k][L];

        for (int i = 0; i < k; ++i) {
            int lowestPrice = prices[0];

            for (int j = 1; j < L; ++j) {
                int price = prices[j];

                // same balance as the yesterday
                int profitWithoutTrading = balance[i][j - 1];
                int profitWithTrading = price - lowestPrice;
                balance[i][j] = Math.max(profitWithoutTrading, profitWithTrading);
                
                // the lowest price reduce previous transaction profit
                // !!! if i == 0, just check price
                lowestPrice = Math.min(lowestPrice, i >= 1 ? price - balance[i - 1][j - 1] : price);
            }
        }

        return balance[k - 1][L - 1];
    }

}
