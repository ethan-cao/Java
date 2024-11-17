package algorithm.leetCode;

/*

You are given an array prices where prices[i] is the price of a given stock on the ith day, 
and an integer fee representing a transaction fee. Find the maximum profit you can achieve. 
You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

1 transaction = buy + sell

1 <= prices.length <= 5 * 10^4
1 <= prices[i] < 5 * 10^4
0 <= fee < 5 * 10^4

#Example
prices = [1,3,2,8,4,9], fee = 2 -> 8
prices = [1,3,7,5,10,3], fee = 3 -> 6

# Analysis
Buy and sell CANNOT happen on the same day

*/

public class M_DP_741 {

    // DP, State Machine
    // 3ms
    public int maxProfit(int[] prices, int fee) {
        int L = prices.length;

        int maxProfitIfBuy = -prices[0];
        int maxProfitIfSell = 0;
        
        for (int i = 1; i < L; ++i) {
            int price = prices[i];
        
            int profitIfBuy = maxProfitIfSell - price; 
            maxProfitIfBuy = Math.max(maxProfitIfBuy, profitIfBuy);

            int profitIfSell = maxProfitIfBuy + price - fee;
            maxProfitIfSell = Math.max(maxProfitIfSell, profitIfSell);
        }
        
        return maxProfitIfSell;
        }

        return profit;
    }
}
