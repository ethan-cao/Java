package algorithm.leetCode;

/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
On each day, you may decide to buy and/or sell the stock. 
You can only hold at most one share of the stock at any time. 
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
 ? buy/sell possible on the same day
   yes, max 1 full cycle of buying and then selling

 ? max buy/sell total
   no limit

*/

public class M_Array_122 {

    public static void main(String[] args) {
        System.out.print(maxProfit3(new int[] { 7, 1, 5, 3, 6, 4 })); // 5
        System.out.print(maxProfit3(new int[] { 7, 6, 4, 3, 1 })); // 0
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxProfit00(int[] prices) {
        int L = prices.length;
        
        // can be simplified to 
        // int maxProfit = 0;
        int[] maxProfits = new int[L];
        
        int lowestBuyPrice = prices[0];
        
        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            // Track the lowest "effective" buy price (price - profit before buying)
            // as there could be multiple transactions
            // consider buy price today as it's allowed
            int effectiveBuyPrice = price - maxProfits[i - 1];
            lowestBuyPrice = Math.min(lowestBuyPrice, effectiveBuyPrice);
            
            // sell with lowestPrice 
            int profit = + price - lowestBuyPrice;
            maxProfits[i] = Math.max(maxProfits[i - 1], profit);
        }
        
        return maxProfits[L - 1];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, 2ms
    public int maxProfit0(int[] prices) {
        final int L = prices.length;     

        // maxProfitsIfHold[i]: max profit if hold, on day i
        int[] maxProfitsIfHold = new int[L];
        // BASE, buy on day 1
        maxProfitsIfHold[0] = -prices[0];

        // maxProfitsIfSold[i]: max profit if have sold, on day i
        int[] maxProfitsIfSold = new int[L];
        // BASE, sell on day 1, but nothing to sell
        maxProfitsIfSold[0] = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];
        
            // profit if buy happens on day i
            // since there is no limit on buy/sell and can hold at most 1 share
            // use (maxProfitsIfSold[i - 1] - price) to get profitIfBuy on day i
            // this means max profit from previous sell, minius price to buy today
            // didnt mention you can sell then immediately buy on the same day, so sell must happen 1 day ahead
            int profitIfHold = maxProfitsIfSold[i - 1] - price;
            maxProfitsIfHold[i] = Math.max(maxProfitsIfHold[i- 1], profitIfHold);

            // profit if sell happens on day i
            // can buy it then immediately sell, so the profit is: maxProfitsIfHold[i] + prices[i]
            int profitIfSold = maxProfitsIfHold[i] + price;
            maxProfitsIfSold[i] = Math.max(maxProfitsIfSold[i - 1], profitIfSold);
        }

       return maxProfitsIfSold[L - 1];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, condensed space
    // 1ms
    public int maxProfit1(int[] prices) {
        final int L = prices.length;     

        int maxProfitIfHold = 0;
        int maxProfitIfSold = 0;

        maxProfitIfHold = -prices[0];
        maxProfitIfSold = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];
        
            int profitIfHold = maxProfitIfSold - price;
            maxProfitIfHold = Math.max(maxProfitIfHold, profitIfHold);

            int profitIfSold = maxProfitIfHold + price;
            maxProfitIfSold = Math.max(maxProfitIfSold, profitIfSold);
        }

       return maxProfitIfSold;
     }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Greedy
     * prices: [p1, p2, p3, p4, p5]
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
