package algorithm.math;

public class GreatestCommonDivisor {

    public static void main(String... args) {
        int a = 12;
        int b = 8;
        System.out.println(" greatestCommonDivisor : " + getGCD1(a, b));

        int[] data = {12, 8, 32, 72};
        int greatestCommonDivisor = 12;  // pick the 1st element itself
        for (int datum : data) {
            greatestCommonDivisor = getGCD1(datum, greatestCommonDivisor);
        }
        System.out.println(" greatestCommonDivisor : " + greatestCommonDivisor);
    }

    /*
        Euclidean algorithm relies on principle : gcd(a, b) = gcd(b, a % b)
        https://en.wikipedia.org/wiki/Euclidean_algorithm

        proof:
            assume g = gcd(a, b)
            a = xg, b = yg

            a = kb + r (a can be represented by b)
            => r = a - kb = xg - kyg = (x-ky)g

            since r = (x-ky)g, x, y and k are integer,
            so g is a divisor for r, and g is also a divisor for b
            so g = gcd(b,r)
            since r = a%b, g = gcd(b, a%b);

            thus, gcd(a, b) => gcd(b, a%b)

            ----------------------
            1. r = a % b
            2. if r == 0， b即为GCD
            3. if r != 0， a = b, b = a % b，返回步骤1
    */

    // Recursion
    public static int getGCD(int a, int b) {
        return a % b == 0 ? b : getGCD(b, a % b);
    }

    // Iteration
    public static int getGCD1(int a, int b) {
        while (b != 0) {
            int remainder = b;
            b = a % b;
            a = remainder;
        }

        return a;
    }
}