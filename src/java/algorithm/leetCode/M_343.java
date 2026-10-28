package algorithm.leetCode;

/*
https://leetcode.com/problems/integer-break/

Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
Return the maximum product you can get.

2 <= n <= 58

### Example
n = 2 -> 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.

n = 10 -> 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.

*/

public class M_343 {
  public int integerBreak(int n) {
    // maxProducts[i]: max product when break integer i
    int[] maxProducts = new int[n + 1];

    // BASE
    maxProducts[1] = 1;

    for (int number = 2; number <= n; ++number) {

      // break it into two parts: num1 and (number-num1)
      // for each number, we can num1 pick from 1 to number - 1

      for (int num1 = 1; num1 < number; ++num1) {
        // product = num1 * （number - num1）

        // 2 ways to get num1
        // either, use num1 directly
        // or,     use maxProduct[num1], max product of integers that sum to num1
        int num1 = Math.max(num1, maxProducts[num1]);
        
        // 2 ways ot get number - num1
        int num2 = Math.max(number - num1, maxProducts[number - num1]);

        int product = num1 * num2;

        maxProducts[number] = Math.max(maxProducts[number], product);
      }
    }

    return maxProducts[n];
  }
}