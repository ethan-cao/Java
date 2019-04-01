package leetCode;

/*
Given a non-negative integer c, your task is to decide whether
there're two integers a and b such that a^2 + b^2 = c.

### Example
5 -> True
Explanation: 1 * 1 + 2 * 2 = 5

3 -->  false

### Condition

### Essential problem

### Corner case

*/

public class E_Math_633 {

    public static void main(String... args) {

        System.out.println(judgeSquareSum(0));  //T
        System.out.println(judgeSquareSum(1));  //T
        System.out.println(judgeSquareSum(4));  //T
        System.out.println(judgeSquareSum(5));  //T

        System.out.println(judgeSquareSum(3));  //F
        System.out.println(judgeSquareSum(7));  //F
        System.out.println(judgeSquareSum(27));  //F

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
}
