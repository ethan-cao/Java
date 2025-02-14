package algorithm.leetCode;

/*
Given a non negative integer number num, for every numbers i in the range 0 ≤ i ≤ num,
calculate the number of 1's in their binary representation and return them as an array.

### Example
2 -> [0,1,1],
5 -> [0,1,1,2,1,2]

*/

import java.util.Arrays;

public class M_DP_BitManipulation_338 {

    public static void main(String... args) {
        System.out.println(Arrays.toString(countBits0(5))); // [0,1,1,2,1,2]
        System.out.println(Arrays.toString(countBits0(10))); // [0,1,1,2,1,2,2,3,1,2,2]
    }

    // DP + Bit manipulation, iterative
    // Time: O(N), 2ms
    public static int[] countBits0(int n) {
        int[] bits = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            // !!! you need (i & 1)
            // alternatively i % 2 == 0
            if ((i & 1) == 0) {
                // if i is even
                // the last bit is 0, countBits(n) is the same as countBits(n >> 1)
                // i >> 1 is a right shift operation, effectively divides i by 2, which contains the same amount of 1
                bits[i] = bits[i >> 1];
            } else {
                // if i is odd
                // the last bit is 1, countBits(n) is countBits(n - 1) + 1
                bits[i] = bits[i - 1] + 1;
            }
        }

        return bits;
    }

    // DP, iterative
    // Time: O(N), 2ms
    public static int[] countBits1(int n) {
        int[] bits = new int[n + 1];

        for (int i = 1; i < n + 1; ++i) {
            if (i % 2 == 0) {
                bits[i] = bits[i / 2];
            } else {
                bits[i] = bits[i / 2] + 1;
            }
        }

        return bits;
    }

    // DP, recursive
    // 4ms
    public int[] countBits2(int n) {
        int[] memo = new int[n + 1];
        int[] ans = new int[n + 1];

        for (int i = 0; i <= n; ++i) {
            ans[i] = count(i, ans, memo);
        }

        return ans;
    }

    private int count(int n, int[] ans, int[] memo) {
        if (n == 0) {
//            ans[n] = 0;
//            memo[n] = ans[n];
//            return memo[n];
            return 0;
        }

        if (memo[n] != 0) {
            return memo[n];
        }


        ans[n] = n % 2 == 0 ? count(n / 2, ans, memo) : count(n - 1, ans, memo) + 1;
        memo[n] = ans[n];

        return memo[n];
    }
}
