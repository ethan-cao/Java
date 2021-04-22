package algorithm.leetCode;

/*
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Note: Given n will be a positive integer.
reach the top : go beyond n steps

### Example
2 -> 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

3 -> 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

### Essential problem
f(n) : how many ways to reach the top
 f(n) = f(n-1) + f(n-2)
 (number of ways for reaching n-1) + (number of ways for reaching n-2)
 there are 2 ways reaching from n-2 to n, 1 is already included in f(n-1)

*/

public class E_DP_70 {

    public static void main(String[] args) {
        System.out.println(climbStairs1(1)); // 1
        System.out.println(climbStairs1(2)); // 2
        System.out.println(climbStairs1(3)); // 3
        System.out.println(climbStairs1(4)); // 5
        System.out.println(climbStairs1(5)); // 8
        System.out.println(climbStairs1(10)); // 89
    }

    // DP, iterative, 0ms
    private static int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }

        // result[n] : how many way to climb to the top
        int[] result = new int[n + 1];
        result[1] = 1;
        result[2] = 2;

        for (int i = 3; i <= n; ++i) {
            result[i] = result[i - 1] + result[i - 2];
        }

        return result[n];
    }

    // DP, recursive, TLE
    private static int climbStairs1(int n) {
        if (n == 1 || n == 2) {
            return n;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

}

