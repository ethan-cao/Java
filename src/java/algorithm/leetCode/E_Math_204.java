package algorithm.leetCode;

/*
Count the number of prime numbers less than a non-negative number, n.

### Example
10 --> 4
There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

*/

public class E_Math_204 {

    public static void main(String... args) {
        System.out.println(countPrimes(3));  // 1 : 2
        System.out.println(countPrimes0(5));  // 2 : 2 and 3
        System.out.println(countPrimes0(10));  // 4 : 2, 3, 5 and 7
        System.out.println(countPrimes0(88));  // 23
        System.out.println(countPrimes(100));  // 25
    }

    // https://www.youtube.com/watch?v=eKp56OLhoQs
    // Sieve of Eratosthenes
    // O(n log log n)
    public static int countPrimes(int n) {
        int count = 0;

        boolean[] isCompositeNumber = new boolean[n];
        // assume all is prime number, by default every element is false
        // T -> composite number,   F -> prime number

        // optimization, check until sqrt(n)
        // since i * j < n, when i = j, i = sqrt(n), the other half is already marked
        for (int i = 2; i < Math.sqrt(n); ++i) {
            if (!isCompositeNumber[i]) {
                for (int j = 2; i * j < n; ++j) {
                    isCompositeNumber[i * j] = true;
                }
            }
        }

        for (int i = 2; i < n; ++i) {
            if (!isCompositeNumber[i]) {
                count++;
            }
        }

        return count;
    }

    // Sieve of Eratosthenes optimization
    public static int countPrimes0(int n) {
        if (n < 3) {
            return 0;
        }

        // only odd number could be prime number
        int count = n / 2;

        boolean[] isCompositeNumber = new boolean[n];
        // assume all is prime number, by default every element is false
        // T -> composite number,   F -> prime number

        // only check odd number
        for (int i = 3; i < Math.sqrt(n); i += 2) {
            // For each composite, do nothing
            if (isCompositeNumber[i]) {
                continue;
            }

            // For each prime, iterate odd composites that can be formed by i
            // (odd: since we only check odd number, formed by i :Sieve of Eratosthenes)
            // i * i is odd composite since i is odd
            // j = i * i is odd, and we need to iterate odd composite number
            // i * i + odd  * i is composite since it is i * (i + odd), but it is even
            // i * i + even * i composite and odd since b (odd) + even * i (even) is odd
            // pick smallest even 2

            for (int j = i * i; j < n; j += 2 * i) { // // j += 2 * i  is  j = j + (2 * i)
                if (!isCompositeNumber[j]) {
                    isCompositeNumber[j] = true;
                    count--;
                }
            }
        }

        return count;
    }

    // Trial division
    public static int countPrimes1(int n) {
        int count = 0;
        n--;

        while (n > 1) {
            if (isPrime(n)) {
                count++;
            }

            n--;
        }

        return count;
    }

    static boolean isPrime(int a) {
        int b = 2;

        // check if there is a number b that is less than a, and a modulo b == 0
        // if so, a is prime number
        // Optimization, examine until sqrt(a)
        while (b <= Math.sqrt(a)) {
            if (a % b == 0) {
                return false;
            }
            b++;
        }

        return true;
    }
}
