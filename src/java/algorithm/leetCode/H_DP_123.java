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


# Analysis
Buy and sell CAN happen on the same day

max profit with at most 2 transaction

*/

public class M_DP_123 {

    // DP, state machine
    public int maxProfit1(int[] prices) {
        final int L = prices.length;

        int profit = 0;

        int profitIfBuy1 = -prices[0];
        int profitIfSell1 = 0;

        int profitIfBuy2 = profitIfSell1 - prices[0];
        int profitIfSell2 = profitIfBuy2 + prices[0];

        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            profitIfBuy1 = Math.max(profitIfBuy1, -price); // assume 1st buy at this price
            profitIfSell1 = Math.max(profitIfSell1, profitIfBuy1 + price);

            profitIfBuy2 = Math.max(profitIfBuy2, profitIfSell1 - price);
            profitIfSell2 = Math.max(profitIfSell2, profitIfBuy2 + price);
        }

        return profitIfSell2;
    }
    
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
