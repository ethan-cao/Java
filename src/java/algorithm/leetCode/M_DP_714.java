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

*/

public class M_DP_741 {

    public static void main(String... args) {
    }

    // DP, State Machine
    // 3ms
    public int maxProfit(int[] prices, int fee) {
        final int L = prices.length;

        int initialBalance = 0;
        
        // 1st day
        int balanceWithoutStock = initialBalance;
        int balanceWithStock = initialBalance - prices[0];

        // 2nd day onwards
        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            int previousBalanceWithoutStock = balanceWithoutStock;
            
            // 2 possible ways to enter without stock state
            // either not hold it since yesterday: balanceWithoutStock
            // or sell one when hold it yesterday and pay the fee: balanceWithStock + price - fee
            balanceWithoutStock = Math.max(balanceWithoutStock, balanceWithStock + price - fee);

            // 2 possible ways to enter with stock state
            // either hold it since yesterday: balanceWithStock
            // or buy one when not hold it yesterday: balanceWithoutStock - price
            balanceWithStock = Math.max(balanceWithStock, previousBalanceWithoutStock - price);
        }

        return Math.max(balanceWithStock, balanceWithoutStock);
    }
}
