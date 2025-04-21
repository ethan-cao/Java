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

    
    // TODO: add base to loop
    // BASE
    maxProducts[1] = 1;

    for (int target = 2; target <= n; ++target) {

      // break it into two parts: num and (target-num)
      // for each target, we can num pick from 1 to target - 1

      for (int num = 1; num < target; ++num) {
        // product = num * （target - num）

        // 2 ways to get num
        // either, use num directly
        // or,     use maxProduct[num], max product of integers that sum to num
        int num1 = Math.max(num, maxProducts[num]);
        
        // 2 ways ot get target - num
        int num2 = Math.max(target - num, maxProducts[target - num]);

        int product = num1 * num2;

        maxProducts[target] = Math.max(maxProducts[target], product);
      }
    }

    return maxProducts[n];
  }
}