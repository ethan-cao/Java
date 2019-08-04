package algorithm.leetCode;

/*
Write a program to find the n-th ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

1 is typically treated as an ugly number.
n does not exceed 1690.

### Example
Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

*/


public class M_DP_264 {

    public static void main(String... args) {
        System.out.println(nthUglyNumber(1)); // 1
        System.out.println(nthUglyNumber(2)); // 2
        System.out.println(nthUglyNumber(3)); // 3
        System.out.println(nthUglyNumber(10)); // 12
    }


    public static int nthUglyNumber(int n) {
        return 1;
    }

}