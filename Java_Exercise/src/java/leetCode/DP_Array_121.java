package leetCode;

/*
#Input
int[] prices

#Output
int result

#Example
[7,1,5,3,6,4]  -> 5
Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5
buy on the first picked day and sell on the second picked day

#Condition
transaction : first but then sell, it can happen on the same day
at most one transaction

#Essential problem
maxProfit = max(prices[idx2] - prices[idx1])
idx2 > idx1

#Solution (algorithm + data structure)

#Corner case

*/
public class DP_Array_121 {

    public static void main(String[] args) {
//        int[] prices = {7, 1, 5, 3, 6, 4};  //5
        int[] prices = {7,6,4,3,1};   // 0
//        int[] prices = {2, 2, 3, 4, 40};

        int maxProfit = maxProfit3(prices);

        System.out.print(maxProfit);
    }

    static int maxProfit1(int[] prices) {
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

    static int maxProfit2(int[] prices) {
        int maxProfit = 0;

        if (prices.length < 2) {
            return maxProfit;
        }

        int minPrice = prices[0];
        for (int i = 0; i < prices.length; ++i) {
            minPrice = Math.min(minPrice, prices[i]);  // minimal price between 0 to i
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }

        return maxProfit;
    }

    // maxSubArray, similar to No.53
    static int maxProfit3(int[] prices) {
        int maxProfit = 0;

        if (prices.length < 2) {
            return maxProfit;
        }

        // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/discuss/39038/Kadane's-Algorithm-Since-no-one-has-mentioned-about-this-so-far-:)-(In-case-if-interviewer-twists-the-input)/36818
        int maxProfitSoFar = 0;
        for (int i = 1; i < prices.length; ++i) {

            maxProfitSoFar += prices[i] - prices[i - 1];
            maxProfitSoFar = Math.max(0, maxProfitSoFar); // make sure it is at least 0
            maxProfit = Math.max(maxProfit, maxProfitSoFar);
        }

        return maxProfit;
    }
}
