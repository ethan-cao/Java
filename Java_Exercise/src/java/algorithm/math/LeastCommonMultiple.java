package algorithm.math;

public class LeastCommonMultiple {

    /*
    a * b = lcm(a, b) * gcd(a, b)
    --------------
    proof

    since d = gcd(a, b),
    a = md, b = nd

    let lcm(a, b) = m = ab / d,    d = ab / m
    assume there is m1, m1 < m
    so d1 = ab / m1 > d

    since d1 = ab / m1,
    so m1 / b = a / d1

    since m1 / b is integer, then a / d1 is integer, and d1 > d;
    this is contradictory that d is the common largest divisor

    ref :  http://www.coursera.org/lecture/number-theory-cryptography/least-common-multiple-3LMq1
     */
    public static void main(String... args) {
        int a = 100;
        int b = 23;

        System.out.println(getLCM(a, b));
    }

    public static int getLCM(int a, int b) {
        return a * b / GreatestCommonDivisor.getGCD(a, b);
    }
}
