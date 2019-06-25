package algorithm.math;

/**
 *  iterated logarithm of n, written log*n (usually read "log star")
 *  it is (the number of times) the logarithm function must be iteratively applied before the result <= 1
 *
 *  In CS, usually use 2 as base
 *
 *  https://en.wikipedia.org/wiki/Iterated_logarithm
 */

public class IteratedLogarithm {

    public static void main(String... args){
        System.out.println(logStar(20)); // base 10 --> 1
        System.out.println(logStar(1000)); // base 10 --> 1
    }

    public static int logStar(int n){
        return log(n, 0);
    }

    private static int log(double n, int times){
        return Math.log10(n) <= 1 ? times : log(Math.log10(n), ++times);
    }
}
