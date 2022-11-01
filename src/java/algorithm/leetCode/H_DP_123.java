package algorithm.leetCode;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete at most two transactions.
You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

1 <= prices.length <= 105
0 <= prices[i] <= 105

#Example
[3,3,5,0,0,3,1,4] -> 6
[1,2,3,4,5] -> 4
[7,6,4,3,1] -> 0

*/

public class M_DP_123 {

    // DP
    // Time: O(N), 5ms
    public int maxProfit0(int[] prices) {
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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // same as 188
    // DP
    // Tim: O(N^2), 10ms
    public int maxProfit(int[] prices) {
        final int L = prices.length;
        final int TRANSACTION_COUNT = 2;

        // maxProfits[i][j]: max profit with i transaction within j days
        int[][] profits = new int[TRANSACTION_COUNT + 1][L];

        for (int t = 1; t <= TRANSACTION_COUNT; ++t) {
            int lowestPrice = prices[0];

            for (int d = 1; d < L; ++d) {
                int price = prices[d];

                // same profit as the day before
                int profitIfNoAction = profits[t][d - 1];

                // the lowest price reduce previous transaction profit from previous day
                lowestPrice = Math.min(lowestPrice, price - profits[t - 1][d - 1]);
                int profitIfSelling = price - lowestPrice;

                profits[t][d] = Math.max(profitIfNoAction, profitIfSelling);
            }
        }

        return profits[TRANSACTION_COUNT][L - 1];
    }

}
