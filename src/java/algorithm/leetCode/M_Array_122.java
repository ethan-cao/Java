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
   yes

 ? max buy/sell per day
   max 1 full cycle of buying and then selling, per day

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
        
        int[] maxProfits = new int[L];
        
        int lowestBuyPrice = prices[0];
        
        for (int i = 1; i < L; ++i) {
            int price = prices[i];
            
            // sell with lowestPrice before today
            int profit = + price - lowestBuyPrice;
            maxProfits[i] = Math.max(maxProfits[i - 1], profit);
            
            // Track the lowest "effective" buy price (price - profit before buying)
            // consider profit from previous transactin
            // as there could be multiple transactions
            int effectiveBuyPrice = price - maxProfits[i - 1];
            lowestBuyPrice = Math.min(lowestBuyPrice, effectiveBuyPrice);
        }
        
        return maxProfits[L - 1];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, 2ms
    public int maxProfit0(int[] prices) {
        final int L = prices.length;     

        // maxProfitsIfBuy[i]: max profit if buy happens in the first i days
        int[] maxProfitsIfBuy = new int[L];
        // BASE, buy on day 1
        maxProfitsIfBuy[0] = -prices[0];

        // maxProfitsIfSell[i]: max profit if sell happens in the first i days
        int[] maxProfitsIfSell = new int[L];
        // BASE, sell on day 1, but nothing to sell
        maxProfitsIfSell[0] = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];
        
            // profit if buy happens on day i
            // since there is no limit on buy/sell and can hold at most 1 share
            // use (maxProfitIfSell[i - 1] - price) to get profitIfBuy on day i
            // this means max profit from previous sell, minius price to buy today
            // didnt mention you can sell then immediately buy on the same day, so sell must happen 1 day ahead
            int profitIfBuy = maxProfitsIfSell[i - 1] - price;
            maxProfitsIfBuy[i] = Math.max(maxProfitsIfBuy[i- 1], profitIfBuy);

            // profit if sell happens on day i
            // can buy it then immediately sell, so the profit is: maxProfitsIfBuy[i] + prices[i]
            int profitIfSell = maxProfitsIfBuy[i] + price;
            maxProfitsIfSell[i] = Math.max(maxProfitsIfSell[i - 1], profitIfSell);
        }

       return maxProfitsIfSell[L - 1];
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
