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
        System.out.println(change1(5, nums1));  // 4

        int[] nums2 = {2};
        System.out.println(change1(3, nums2));  // 0

        int[] nums3 = {10};
        System.out.println(change1(10, nums3));  // 1

        int[] nums4 = {3, 5, 7, 8, 9, 10, 11};
        System.out.println(change1(500, nums4));  // 35502874
    }

    // DP, recursive, top-down, DFS,  O(2^n), too slow to be accepted
    public static int change(int amount, int[] coins) {
        count = 0;
        changeCoin(amount, coins, 0);
        return count;
    }

    private static int count = 0;

    private static void changeCoin(int amount, int[] coins, int idx) {
        if (amount == 0) {
            count++;
            return;
        }

        for (int i = idx; i < coins.length; ++i) {
            if (amount >= coins[i]) {
                changeCoin(amount - coins[i], coins, i);
            }
        }
    }


    // DP, iterative, bottom-up
    public static int change1(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }

        int[][] solutions = new int[coins.length + 1][amount + 1];

        for (int i = 1; i <= coins.length; ++i) {
            for (int j = 1; j <= amount; ++j) {
                int coin = coins[i - 1];

                if (coin == j) {
                    solutions[i][j] = solutions[i - 1][j] + 1;
                } else if (coin > j) {
                    solutions[i][j] = solutions[i - 1][j];
                } else {
                    solutions[i][j] = solutions[i - 1][j] + solutions[i][j - coin];
                }
            }
        }

        return solutions[coins.length][amount];
    }
}