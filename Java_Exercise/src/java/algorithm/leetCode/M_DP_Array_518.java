package algorithm.leetCode;

/*
You are given coins of different denominations and a total amount of money.
Write a function to compute the number of combinations that make up that amount.
You may assume that you have infinite number of each kind of coin.

assume that

0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer


### Example
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Input: amount = 10, coins = [10]
Output: 1

Related :  416 0-1 knapsack problem
*/


public class M_DP_Array_518 {

    public static void main(String... args) {
        int[] nums1 = {1, 2, 5};
        System.out.println(change(5, nums1));  // 4

    }

    public static int change(int amount, int[] coins) {
        return 1;
    }


}