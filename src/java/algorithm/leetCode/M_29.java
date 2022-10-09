package algorithm.leetCode;

/*
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
Return the quotient after dividing dividend by divisor.
The integer division should truncate toward zero, which means losing its fractional part.
For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
-2^31 <= dividend, divisor <= 2^31 - 1
divisor != 0

Note:
Assume we are dealing with an environment that could only store integers within
the 32-bit signed integer range: [−231,  231 − 1].
For this problem, assume that your function returns 231 − 1 when the division result overflows.

### Example
Input: dividend = 10, divisor = 3
Output: 3

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = truncate(-2.33333..) = -2.

Input: dividend = 0, divisor = 1
Output: 0

Input: dividend = 1, divisor = 1
Output: 1

*/

public class M_29 {

    // the quotient of a division is just the number of times that we can subtract the divisor from the dividend without making it negative.
    // Suppose dividend = 15 and divisor = 3, 15 - 3 > 0. We now try to subtract more by shifting 3 to the left by 1 bit (6).
    // Since 15 - 6 > 0, shift 6 again to 12. Now 15 - 12 > 0, shift 12 again to 24, which is larger than 15.
    // So we can at most subtract 12 from 15. Since 12 is obtained by shifting 3 to left twice, it is 1 << 2 = 4 times of 3.
    // We add 4 to an answer variable (initialized to be 0). The above process is like 15 = 3 * 4 + 3.
    // We now get part of the quotient (4), with a remaining dividend 3.
    // Then we repeat the above process by subtracting divisor = 3 from the remaining dividend = 3 and obtain 0. We are done.
    // In this case, no shift happens. We simply add 1 << 0 = 1 to the answer variable.

    // ... did not understand why it works
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int quotient = 0;
        boolean isNegative = dividend < 0 ^ divisor < 0; // ^: XOR, returns true when operands are different
        int positiveDividend = Math.abs(dividend);
        int positiveDivisor = Math.abs(divisor);

        while (positiveDividend - positiveDivisor >= 0) {
            int shift = 0;
            while (positiveDividend - (positiveDivisor << shift << 1) >= 0) {  // << shift happens first, then << 1
                shift++;
            }

            quotient += 1 << shift;
            positiveDividend -= positiveDivisor << shift;
        }

        return isNegative ? -quotient : quotient;
    }

}