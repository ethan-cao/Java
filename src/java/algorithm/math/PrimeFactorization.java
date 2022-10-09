package algorithm.math;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PrimeFactorization {

    public static void main(String... args) {
        System.out.println(Collections.singletonList(primeFactorize(2343)));
    }

    /**
     * return Map<prime, power> :  n = ∑(prime^power)
     */
    public static Map<Integer, Integer> primeFactorize(int n) {
        Map<Integer, Integer> results = new LinkedHashMap<>();

        if (n < 2) {
            return results;
        }

        if (n == 2) {
            results.put(2, 1);
            return results;
        }

        if (n == 3) {
            results.put(3, 1);
            return results;
        }

        while (n % 2 == 0) {
            Integer count = results.get(2);
            if (count == null) {
                count = 0;
            }
            count++;

            results.put(2, count);

            n /= 2;
        }

        for (int i = 3; i < Math.sqrt(n); i += 2) {
            int key = i;

            while (n % key == 0) {
                Integer count = results.get(key);
                if (count == null) {
                    count = 0;
                }
                count++;
                results.put(key, count);

                n /= key;
            }
        }

        if (n != 1) {
            results.put(n, 1);
        }

        return results;
    }
}
