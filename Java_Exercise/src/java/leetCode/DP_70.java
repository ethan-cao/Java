package leetCode;

/*
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Note: Given n will be a positive integer.
reach the top : go beyond n steps

### Input
int step

### Output
int solution

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

### Condition
n is positive integer

### Essential problem  / Sub problem
DP

f(n) : how many ways to reach the top
 f(n) = f(n-1) + f(n-2)
 (number of ways for reaching n-1) + (number of ways for reaching n-2)
 there are 2 ways reaching from n-2 to n, 1 is already included in f(n-1)

 this formula reminds one Fibonacci

 f(1) = 1
 f(2) = 2
 f(3) = 3
 f(4) = 5

 ======== climbStairs1


### Corner case

*/

public class DP_70 {

    public static void main(String[] args) {
        int solution = climbStairs2(5);
        solution = climbStairs2(10);

        System.out.println(solution);
    }

    // Fibonacci
    private static int climbStairs1(int n) {
        if (1 == n || 2 == n) {
            return n;
        }

        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    // Fibonacci, faster than recursion
    private static int climbStairs2(int n) {
        if (1 == n || 2 == n) {
            return n;
        }

        int waysToStep1 = 1;
        int waysToStep2 = 2;
        int waysToStepN = 0;

        for (int i = 2; i < n; ++i) {
            waysToStepN = waysToStep1 + waysToStep2;
            waysToStep1 = waysToStep2;
            waysToStep2 = waysToStepN;
        }

        return waysToStepN;
    }
}

