package algorithm.leetCode;

/*
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

    for (int target = 2; target <= n; ++target) {
      // for each integer target, we can pick from 1 to target - 1
      for (int num = 1; num < target; ++num) {

        // product = num1 * num2, if num1 + num2 = target

        // there are 2 possible way to get value num
        // either by the num itself, or break num to integers
        int num1 = Math.max(num, maxProducts[num]);
        int num2 = Math.max(target - num, maxProducts[target - num]);

        int product = num1 * num2;

        maxProducts[target] = Math.max(maxProducts[target], product);
      }
    }

    return maxProducts[n];
  }
}