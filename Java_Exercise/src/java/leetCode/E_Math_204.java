package leetCode;

/*
Count the number of prime numbers less than a non-negative number, n.


### Example
10 --> 4
There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

### Condition

### Essential problem

### Corner case

*/

public class E_Math_204 {
    public static void main(String... args) {
        System.out.println(countPrimes(3));  // 1 : 2
        System.out.println(countPrimes(5));  // 2 : 2 and 3
        System.out.println(countPrimes(10));  // 4 : 2, 3, 5 and 7
        System.out.println(countPrimes(88));  // 23
        System.out.println(countPrimes(100));  // 25
    }

    // https://www.youtube.com/watch?v=eKp56OLhoQs
    // Sieve of Eratosthenes
    public static int countPrimes(int n) {
        int count = 0;
        boolean[] compositeNumbers = new boolean[n];
        // store 2, 3, ... n
        // T -> composite number,   F -> prime number

        for (int i = 2; i < Math.sqrt(n); ++i) {
            if (!compositeNumbers[i]) {
                for (int j = 2; i * j < n; ++j) {
                    compositeNumbers[i * j] = true;
                }
            }
        }

        for (int i = 2; i < n; ++i) {
            if (!compositeNumbers[i]) {
                count++;
            }
        }

        return count;
    }

    // Trial division
    public static int countPrimes0(int n) {
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
        while (b <= Math.sqrt(a)) {  // Optimization, just need to examine until sqrt(a)
            if (a % b == 0) {
                return false;
            }

            b++;
        }

        return true;
    }

}
