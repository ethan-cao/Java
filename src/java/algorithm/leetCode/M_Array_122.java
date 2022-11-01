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

# Analysis
Buy and sell can happen on the same day

*/

public class M_Array_122 {

    public static void main(String[] args) {
        System.out.print(maxProfit3(new int[] { 7, 1, 5, 3, 6, 4 })); // 5
        System.out.print(maxProfit3(new int[] { 7, 6, 4, 3, 1 })); // 0
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* DP 
     *   prices: [p1, p2, p3, p4, p5]
     */
    public int maxProfit1(int[] prices) {
        final int L = prices.length;     

        int profit = 0;
        int profitIfBuying =  0;
        int profitIfSelling = 0;

        for (int i = 0; i < L; ++i) {
            int price = prices[i];

            // no action or buying
            profitIfBuying =  Math.max(profitIfBuying, profit - price);
            
            // no action or selling
            profitIfSelling = Math.max(profitIfSelling, profit + price);
        
            profit = Math.max(profitIfBuying, profitIfSelling);
        }
    
        return profit;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Greedy
     *   prices: [p1, p2, p3, p4, p5]
     */
    public int maxProfit(final int[] prices) {
        final int L = prices.length;

        int profit = 0;

        for (int i = 1; i < L; ++i) {
            int profitIfSelling = prices[i] - prices[i - 1];
            
            // as long as there is profit, count it in
            profitIfSelling += Math.max(0, profitIfSelling);
        }

        return profit;
    }
}
