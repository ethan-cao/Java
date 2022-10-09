package algorithm.leetCode;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete at most two transactions.
You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

1 <= prices.length <= 105
0 <= prices[i] <= 105

#Example
[3,3,5,0,0,3,1,4] -> 6
[1,2,3,4,5] -> 4
[7,6,4,3,1] -> 0

*/

public class M_DP_123 {

    public static void main(String... args) {
    }

    // DP
    // Time: O(N), 5ms
    public int maxProfit0(int[] prices) {
        final int L = prices.length;

        int balance1 = 0;
        int lowestPrice1 = prices[0];

        int balance2 = 0;
        int lowestPrice2 = prices[0];

        for (int i = 1; i < L; ++i) {
            int price = prices[i];

            int profitWithoutTrading1 = balance1;
            int profitWithTrading1 = price - lowestPrice1;
            balance1 = Math.max(profitWithoutTrading1, profitWithTrading1);
            lowestPrice1 = Math.min(lowestPrice1, price);

            int profitWithoutTrading2 = balance2;
            int profitWithTrading2 = price - lowestPrice2;
            balance2 = Math.max(profitWithoutTrading2, profitWithTrading2);
            lowestPrice2 = Math.min(lowestPrice2, price - balance1);
        }

        return balance2;
    }

    // DP
    // Tim: O(N^2), 10ms
    public int maxProfit(int[] prices) {
        final int L = prices.length;

        int transactionCount = 2;

        // maxProfits[i][j]: max profit with i transaction within j days
        int[][] maxProfits = new int[transactionCount + 1][L];

        for (int t = 1; t <= 2; t++) {
            int lowestPrice = prices[0];

            for (int i = 1; i < L; i++) {
                int price = prices[i];

                // same profit as the day before
                int profitWithoutTrading = maxProfits[t][i - 1];

                // the lowest price reduce previous transaction profit
                lowestPrice = Math.min(lowestPrice, price - maxProfits[t - 1][i - 1]);
                int profitWithTrading = price - lowestPrice;

                maxProfits[t][i] = Math.max(profitWithoutTrading, profitWithTrading);
            }
        }

        return maxProfits[transactionCount][L - 1];
    }

}
