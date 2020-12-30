package algorithm.leetCode;

/*
Given a non-negative integer c, your task is to decide whether
there're two integers a and b such that a^2 + b^2 = c.

### Example
5 -> True,  Explanation: 1 * 1 + 2 * 2 = 5
3 -->  false

*/

import algorithm.math.PrimeFactorization;

import java.util.*;

public class E_Math_633 {

    public static void main(String... args) {

//        System.out.println(judgeSquareSum3(0));  //T
//        System.out.println(judgeSquareSum3(1));  //T
//        System.out.println(judgeSquareSum3(4));  //T
//        System.out.println(judgeSquareSum3(5));  //T
//
//        System.out.println(judgeSquareSum3(3));  //F
//        System.out.println(judgeSquareSum3(7));  //F
        System.out.println(judgeSquareSum3(27));  //F
//        System.out.println(judgeSquareSum3(315));  //F

    }

    public static boolean judgeSquareSum(int c) {
        int upperLimit = (int) Math.sqrt(c / 2);

        for (int i = 0; i <= upperLimit; ++i) {
            double sqrt = Math.sqrt(c - i * i);

            if (sqrt == (int) sqrt) {
                return true;
            }
        }

        return false;
    }

    public static boolean judgeSquareSum1(int c) {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i <= Math.sqrt(c / 2); ++i) {
            // store all possible a*a
            set.add(i * i);

            // if ever b*b's corresponding a*a is already checked, then return true
            if (set.contains(c - i * i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean judgeSquareSum2(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);

        while (left <= right) {
            int value = left * left + right * right;

            if (c > value) {
                left++;
            } else if (c < value) {
                right--;
            } else {
                return true;
            }
        }

        return false;
    }

    // NOT working
    // https://en.wikipedia.org/wiki/Sum_of_two_squares_theorem
    // a number is a sum of two squares
    // iff   each prime factor that’s 3 modulo 4   occurs to an even power    in the number's prime factorization.
    public static boolean judgeSquareSum3(int n) {
        Map<Integer, Integer> primes = PrimeFactorization.primeFactorize(n);

        for (Map.Entry<Integer, Integer> entry : primes.entrySet()){
            int prime = entry.getKey();
            int power = entry.getValue();

            if( ( (prime - 4) % 3 == 0) ){
                return false;
            }
        }

        return true;
    }


}
