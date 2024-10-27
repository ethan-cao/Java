package algorithm.leetCode;

/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. 
However, you can buy it then immediately sell it on the same day.
Find and return the maximum profit you can achieve.

#Example
[7,1,5,3,6,4] -> 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

[1,2,3,4,5] -> 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.

[7,6,4,3,1] -> 0

*/

public class M_Array_122 {

    public static void main(String[] args) {
        System.out.print(maxProfit3(new int[] { 7, 1, 5, 3, 6, 4 })); // 5
        System.out.print(maxProfit3(new int[] { 7, 6, 4, 3, 1 })); // 0
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, 2ms
    public int maxProfit0(int[] prices) {
        final int L = prices.length;     

        // maxProfitIfBuy[i]: max profit if buy happens in the first i days
        int[] maxProfitIfBuy = new int[L];
        // maxProfitIfSell[i]: max profit if sell happens in the first i days
        int[] maxProfitIfSell = new int[L];

        // BASE
        // buy on day 1
        maxProfitIfBuy[0] = -prices[0];
        // sell on day 1, but nothing to sell
        maxProfitIfSell[0] = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];
        
            // profit if buy happens on day i
            // can hold at most 1 share, 
            // didt mention you can sell then immediately buy on the same day, so sell must happen 1 day ahead
            // so the profit is: maxProfitIfSell[i - 1] - prices[i]
            int profitIfBuy = maxProfitIfSell[i - 1] - price;
            maxProfitIfBuy[i] = Math.max(maxProfitIfBuy[i- 1], profitIfBuy);

            // profit if sell happens on day i
            // can buy it then immediately sell, so the profit is: maxProfitIfBuy[i] + prices[i]
            int profitIfSell = maxProfitIfBuy[i] + price;
            maxProfitIfSell[i] = Math.max(maxProfitIfSell[i - 1], profitIfSell);
        }

       return maxProfitIfSell[L - 1];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, condensed space
    // 1ms
    public int maxProfit1(int[] prices) {
        final int L = prices.length;     

        int maxProfitIfBuy = 0;
        int maxProfitIfSell = 0;

        maxProfitIfBuy = -prices[0];
        maxProfitIfSell = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];
        
            int profitIfBuy = maxProfitIfSell - price;
            maxProfitIfBuy = Math.max(maxProfitIfBuy, profitIfBuy);

            int profitIfSell = maxProfitIfBuy + price;
            maxProfitIfSell = Math.max(maxProfitIfSell, profitIfSell);
        }

       return maxProfitIfSell;
     }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Greedy
     *   prices: [p1, p2, p3, p4, p5]
     */
    public int maxProfit(final int[] prices) {
        final int L = prices.length;

        int profit = 0;

        for (int i = 1; i < L; ++i) {
            int profitIfSell = prices[i] - prices[i - 1];
            
            // as long as there is profit, count it in
            profitIfSell += Math.max(0, profitIfSell);
        }

        return profit;
    }
}
