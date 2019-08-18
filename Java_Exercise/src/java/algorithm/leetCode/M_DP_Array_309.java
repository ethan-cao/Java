package algorithm.leetCode;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit.You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (must sell the stock before you buy again)
After you sell your stock, you cannot buy stock on next day. (cooldown 1 day)

### Example
[1,2,3,0,2] -> 3
transactions = [buy, sell, cooldown, buy, sell]

Sub problem

transaction = first buy then sell
cooldown at least 1 day between each transaction


Related : 121
*/

public class M_DP_Array_309 {

    public static void main(String... args) {
        int[] prices = {};
        System.out.println(maxProfit(prices)); // 0

        int[] prices1 = {1};
        System.out.println(maxProfit(prices1)); // 0

        int[] prices3 = {1, 2, 3, 0};
        System.out.println(maxProfit(prices3)); // 2

        int[] prices4 = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices4)); // 3

        int[] prices5 = {41, 23, 53, 2, 2, 9, 3, 1, 2, 2};
        System.out.println(maxProfit(prices5)); // 38
    }

    public static int maxProfit(int[] prices) {
        if (null == prices || prices.length < 2) {
            return 0;
        }

        // buys[i] :  max profit til day i with the last action as buy
        int[] buys = new int[prices.length];
        // sells[i] : max profit til day i with the last action as sell
        int[] sells = new int[prices.length];

        buys[0] = -prices[0];
        buys[1] = Math.max(buys[0], -prices[1]);
        sells[1] = prices[1] > prices[0] ? prices[1] - prices[0] : 0;

        for (int i = 2; i < prices.length; ++i) {
            buys[i] = Math.max(buys[i - 1], sells[i - 2] - prices[i]); // coz cooldown, sell on i-2
            sells[i] = Math.max(sells[i - 1], buys[i - 1] + prices[i]);
        }

        return sells[prices.length - 1];
    }


}
