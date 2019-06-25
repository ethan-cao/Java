package algorithm.math;

public class GreatestCommonDivisor {

    public static void main(String... args) {
        int[] a = {12, 8, 32};

        int greatestCommonDivisor = 0;
        for (int number : a) {
            greatestCommonDivisor = getGreatestCommonDivisor1(number, greatestCommonDivisor);
        }

        System.out.println(" greatestCommonDivisor : " + greatestCommonDivisor);
    }

    /*
        https://en.wikipedia.org/wiki/Euclidean_algorithm

        Euclidean algorithm relies on principle : gcd(a, b) = gcd(b, a % b)

        proof:
            assume g = gcd(a, b)
            a = xg, b = yg

            a = kb + r (a can be represented by b)
            => r = a - kb = xg - kyg = (x-ky)g

            since r = (x-ky)g, x, y and k are integer,
            so g is divisor for r, and g is also divisor for b
            so g = gcd(b,r)
            since r = a%b, g = gcd(b, a%b);

            thus, gcd(a, b) => gcd(b, a%b)

            ----------------------
            1. a % b得余数r
            2. 若r == 0，则b即为GCD
            3. 若r != 0，则a = b, b = r，返回步骤1
    */


    // Recursion
    public static int getGreatestCommonDivisor(int a, int b) {
        return b > 0 ? getGreatestCommonDivisor(b, a % b) : a;
    }

    // Iteration
    public static int getGreatestCommonDivisor1(int a, int b) {
        while (b > 0) {
            int remainder = b;
            b = a % b;
            a = remainder;
        }

        return a;
    }
}
