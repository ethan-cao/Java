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

    // DP, 4ms 
    public int maxProfit1(int[] prices) {
        final int L = prices.length;

        // you can have at most 2 transactions
        // there is no limit on how many actions per day

        // max profit if there is 1 transaction
        int maxProfitIfBuy1 = -prices[0];
        int maxProfitIfSell1 = 0;

        // max profit if there are 2 transaction
        int maxProfitIfBuy2 = maxProfitIfSell1 - prices[0];
        int maxProfitIfSell2 = maxProfitIfBuy2 + prices[0];

        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            // the 1st transaction happens on day i
            maxProfitIfBuy1 = Math.max(maxProfitIfBuy1, -price); 
            maxProfitIfSell1 = Math.max(maxProfitIfSell1, maxProfitIfBuy1 + price);

            // the 2nd transaction happens on day i
            maxProfitIfBuy2 = Math.max(maxProfitIfBuy2, maxProfitIfSell1 - price);
            maxProfitIfSell2 = Math.max(maxProfitIfSell2, maxProfitIfBuy2 + price);
        }

        return maxProfitIfSell2;
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
