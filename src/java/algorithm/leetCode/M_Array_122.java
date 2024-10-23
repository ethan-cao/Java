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
        int L = prices.length;

        // there is no limit on how many transaction can happen
        
        // profitIfBuy[i]: max profit if buy happen on day i
        int[] profitIfBuy = new int[L];
        // profitIfBuy[i]: max profit if sell happen on day i
        int[] profitIfSell = new int[L];

        profitIfBuy[0] = -prices[0];
        profitIfSell[0] = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            profitIfBuy[i] = Math.max(profitIfBuy[i - 1], profitIfSell[i - 1] - price);
            
            // you can buy it then immediately sell, so the profit is: profitIfBuy[i] + price
            profitIfSell[i] = Math.max(profitIfSell[i - 1], profitIfBuy[i] + price);
        }
        
        return profitifsell[l - 1];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, state machine
    // 1ms
    public int maxProfit1(int[] prices) {
        final int L = prices.length;     
     
        int profitIfBuy = -prices[0];
        int profitIfSell = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            // compare the profit between buy on day i or privious day
            profitIfBuy = Math.max( profitIfSell - price, profitIfBuy);
            // compare the profit between sell on day i or privious day
            profitIfSell = Math.max(profitIfBuy + price, profitIfSell);
        }

        return profitIfSell;
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
