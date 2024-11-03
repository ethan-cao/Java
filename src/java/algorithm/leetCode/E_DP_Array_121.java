package algorithm.leetCode;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock
and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction.
If you cannot achieve any profit, return 0.

1 <= prices.length <= 10^5
0 <= prices[i] <= 10^4

#Example
[7,1,5,3,6,4]  -> 5
Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5
buy on the first picked day and sell on the second picked day

# Analysis
1 Buy and sell CANNOT happen on the same day
2 !!! get max profit with at most 1 transaction


*/

public class E_DP_Array_121 {

    public static void main(String[] args) {
        System.out.print(maxProfit3(new int[] { 7, 1, 5, 3, 6, 4 })); // 5
        System.out.print(maxProfit3(new int[] { 7, 6, 4, 3, 1 })); // 0
    }

    // DP, 4ms
    public int maxProfit0(int[] prices) {
        final int L = prices.length;

        // max 1 transaction
        // 1 transaction means a full cycle of buying and then selling
        // buy and sell must on different days

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
            int profitIfBuy = -price;
            maxProfitIfBuy[i] = Math.max(maxProfitIfBuy[i - 1], profitIfBuy);

            // profit if sell happens on day i
            // since sell must on a different day, use maxProfitIfBuy[i - 1] 
            int profitIfSell = maxProfitIfBuy[i - 1] + price;
            maxProfitIfSell[i] = Math.max(maxProfitIfSell[i - 1], profitIfSell);
        }

        // obviously, you make profit if you sell
        return maxProfitIfSell[L - 1];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, condensed space, 2ms
    public int maxProfit1(int[] prices) {
        final int L = prices.length;

        // max profit if buy on day1
        int maxProfitIfBuy = -prices[0];
        // max profit if sell on day1
        int maxProfitIfSell = 0;

        for (int i = 1; i < L; ++i) {
            int price = prices[i];
        
            int profitIfBuy = -price;
            int preMaxProfitIfBuy = maxProfitIfBuy; 
            maxProfitIfBuy = Math.max(maxProfitIfBuy, profitIfBuy);

            // since sell must on a different day, use maxProfitIfBuy[i - 1] 
            // need to use the max profit when buy happens before dayI
            int profitIfSell = preMaxProfitIfBuy + price;
            maxProfitIfSell = Math.max(maxProfitIfSell, profitIfSell);

        }
    
        return maxProfitIfSell;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP
    // 2ms
    // related: 123, 188
    public static int maxProfit(int[] prices) {
        final int L = prices.length;
    
        int profit = 0;
    
        int lowestPrice = prices[0];
    
        for (int i = 1; i < L; ++i) {
            int price  = prices[i];
            
            // 2 possible action: buy and sell
            // to get profit, we just need to consider selling
            int profitIfSell = price - lowestPrice;

            profit = Math.max(profit, profitIfSell);
        
            lowestPrice = Math.min(lowestPrice, price);
        }

        return profit;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // this is not meant for interview
    // DP, 10ms
    // Kadane's Algorithm
    /*
     * suppose there are 5 days, profit = pj - pi, 1 <= i < j <= 5
     * price [p1, p2, p3, p4, p5]
     * delta [ d2, d3, d4, d5]
     * 
     * buy on day 1, sell on day 2: p2 - p1 = d2
     * 
     * buy on day 2, sell on day 3: p3 - p2 = d3
     * buy on day 1, sell on day 3: p3 - p1 = (p3 - p2) + (p2 - p1) = d3 + d2
     * 
     * buy on day 3, sell on day 4: p4 - p3 = d4
     * buy on day 2, sell on day 4: p4 - p2 = (p4 - p3) + (p3 - p2) = d4 + d3
     * buy on day 1, sell on day 4: p4 - p1 = (p4 - p3) + (p3 - p2) + (p2 - p1) = d4 + d3 + d2
     * 
     * buy on day 4, sell on day 5: p5 - p4 = d5
     * buy on day 3, sell on day 5: p5 - p3 = (p5 - p4) + (p4 - p3) = d5 + d4
     * buy on day 2, sell on day 5: p5 - p2 = (p5 - p4) + (p4 - p3) + (p3 - p2) = d5 + d4 + d3
     * buy on day 1, sell on day 5: p5 - p1 = (p5 - p4) + (p4 - p3) + (p3 - p2) + (p2 - p1) = d5 + d4 + d3 + d2
     * 
     * so the max sub array sum in delta[] is the max profit
     */
    public static int maxProfit3(int[] prices) {
        final int L = prices.length;

        if (L == 1) {
            return 0;
        }

        int[] delta = new int[L];

        for (int i = 1; i < L; ++i) {
            delta[i] = prices[i] - prices[i - 1];
        }

        int maxSum = Math.max(0, delta[1]);
        int localMaxSum = Math.max(0, delta[1]);

        for (int i = 2; i < L; ++i) {
            int num = delta[i];

            int newArraySum = Math.max(0, num);
            int appendingArraySum = Math.max(0, localMaxSum + num);
            localMaxSum = Math.max(newArraySum, appendingArraySum);

            maxSum = Math.max(maxSum, localMaxSum);
        }

        return maxSum;
    }

    // brute force
    static int maxProfit11(int[] prices) {
        int maxProfit = 0;

        if (prices.length < 2) {
            return maxProfit;
        }

        int profit = 0;

        for (int i = 0; i < prices.length; ++i) {
            for (int j = i + 1; j < prices.length; ++j) {
                profit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }
}
