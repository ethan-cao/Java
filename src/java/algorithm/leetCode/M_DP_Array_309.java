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

*/

public class M_DP_Array_309 {

    public static void main(String... args) {
        System.out.println(maxProfit(new int[]{})); // 0
        System.out.println(maxProfit(new int[]{1})); // 0
        System.out.println(maxProfit(new int[]{1, 2, 3, 0})); // 2
        System.out.println(maxProfit(new int[]{1, 2, 3, 0, 2})); // 3
        System.out.println(maxProfit(new int[]{41, 23, 53, 2, 2, 9, 3, 1, 2, 2})); // 38
    }

    // DP, iterative, 0ms
    // Time: O(N), Space: O(N)
    public static int maxProfit(int[] prices) {
        if (null == prices || prices.length < 2) {
            return 0;
        }

        final int L = prices.length;

        // buys[i]: max profit made from day 0 to day i, with the last action as buy or not buy
        int[] buys = new int[L];
        // sells[i]: max profit made from day 0 to day i, with the last action as sell or not sell
        int[] sells = new int[L];

        buys[0] = -prices[0];
        buys[1] = Math.max(buys[0], -prices[1]);
        sells[0] = 0;
        sells[1] = Math.max(sells[0], buys[0] + prices[1]); // must sell after buy, so buy[0] must be -price[0]

        for (int i = 2; i < L; ++i) {
            buys[i] = Math.max(buys[i - 1], sells[i - 2] - prices[i]);
            sells[i] = Math.max(sells[i - 1], buys[i - 1] + prices[i]);
        }

        return Math.max(buys[L - 1], sells[L - 1]);
    }

    // DP, iterative, condensed space, 0ms
    // Time: O(N), Space: O(1)
    public static int maxProfit1(int[] prices) {
        if (null == prices || prices.length < 2) {
            return 0;
        }

        final int L = prices.length;

        int buyPrevious = -prices[0];
        int buy = Math.max(buyPrevious, -prices[1]);
        int sellPrevious = 0;
        int sell = Math.max(sellPrevious, buyPrevious + prices[1]);

        for (int i = 2; i < L; ++i) {
            buyPrevious = buy;
            buy = Math.max(buyPrevious, sellPrevious - prices[i]);

            sellPrevious = sell;
            sell = Math.max(sellPrevious, buyPrevious + prices[i]);
        }

        return Math.max(buy, sell);
    }
}
