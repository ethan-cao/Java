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

    // DP, 2ms 
    public int maxProfit1(int[] prices) {
        final int L = prices.length;

        // you can have at most 2 transactions
        // 1 transaction means a full cycle of buying and then selling
        // there is no limit on how many actions per day

        // max profit if 1st buy on day1
        int maxProfitIfBuy1 = -prices[0];
        // max profit if 1st sell on day1
        int maxProfitIfSell1 = 0;
    
        // max profit if 2nd buy on day1
        int maxProfitIfBuy2 = -prices[0];
        // max profit if 2nd sell on day1
        int maxProfitIfSell2 = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];
            
            // profit if 1st buy on dayi
            int profitIfBuy1 = -price;
            maxProfitIfBuy1 = Math.max(maxProfitIfBuy1, profitIfBuy1);

            // profit if 1st sell on dayi, must happen after buy1
            int profitIfSell1 = maxProfitIfBuy1 + price;
            maxProfitIfSell1 = Math.max(maxProfitIfSell1, profitIfSell1);

            // profit if 2nd buy on dayi, must happen after sell1
            int profitIfBuy2 = maxProfitIfSell1 - price;
            maxProfitIfBuy2 = Math.max(maxProfitIfBuy2, profitIfBuy2);

            // profit if 2nd sell on dayi, must happen after buy2
            int profitIfSell2 = maxProfitIfBuy2 + price;
            maxProfitIfSell2 = Math.max(maxProfitIfSell2, profitIfSell2);
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
