package algorithm.leetCode;

/*
Implement pow(x, n), which calculates x raised to the power n (i.e. x^n).
-100.0 < x < 100.0
-2^31 <= n <= 2^31-1
-10^4 <= xn <= 10^4


### Example
Input: x = 2.00000, n = 10
Output: 1024.00000

Input: x = 2.10000, n = 3
Output: 9.26100

Input: x = 2.00000, n = -2
Output: 0.25000

*/

public class M_50 {

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0d;
        }

        if(n == Integer.MIN_VALUE){
            x = x * x;
            n = n/2;
        }

        if (n < 0) {
            x = 1/x;
            n = -n; // MIN_INT is -2147483648 but MAX_INT is 2147483647 ,so n = -n is failed!!
        }

        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

}