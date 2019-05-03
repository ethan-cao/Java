package leetCode;

/*
Given an integer, write a function to determine if it is a power of two.

### Example
1 -> true, Explanation: 2^0 = 1
16 -> true, Explanation: 2^4 = 16
218 -> false

### Condition

### Essential problem
Decimal to hexadecimal

### Corner case

*/

import java.util.Arrays;
import java.util.HashSet;

public class E_Math_BitManipulation_231 {

    public static void main(String... args) {
//        System.out.println(isPowerOfTwo(1));   // t
//        System.out.println(isPowerOfTwo(16));  // t
//        System.out.println(isPowerOfTwo(218));  // f
        System.out.println(isPowerOfTwo(2147483647));  // f, 2147483647 = 2^31 - 1, max signed int
    }

    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        int i = 0;
        long power = 1;
        // if use int, any value that is larger than the largest signed int 2^31 - 1, will just be 2^31 - 1
        // when input is 2147483647, 2^30 < 2147483647, it will try 2^31, which is larger than 2^31 - 1
        // then it is 2^31 - 1 and return true, but it should be false

        while (power <= n) {
            if (power == n) {
                return true;
            }

            power = (long) Math.pow(2, ++i);
        }

        return false;
    }

    public static boolean isPowerOfTwo1(int n) {
        while (n > 0){
           n /= n/2;

           if (n == 1){
               return true;
           }
        }


        return false;
    }

    // There are only 31 numbers in total for an 32-bit integer.
    public static boolean isPowerOfTwo2(int n) {
        return new HashSet<>(Arrays.asList(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824)).contains(n);
    }
}
