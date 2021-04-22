package algorithm.leetCode;

/*
Given a non negative integer number num, for every numbers i in the range 0 ≤ i ≤ num,
calculate the number of 1's in their binary representation and return them as an array.

### Example
2 -> [0,1,1],
5 -> [0,1,1,2,1,2]

*/

import java.util.Arrays;

public class M_DP_BitManipulation_338 {

    public static void main(String... args) {
        System.out.println(Arrays.toString(countBits(5))); // [0,1,1,2,1,2]
        System.out.println(Arrays.toString(countBits(10))); // [0,1,1,2,1,2,2,3,1,2,2]
    }

    public static int[] countBits(int num) {
        int[] bits = new int[num + 1];

        for (int i = 1; i <= num; ++i) {
            if ((i & 1) == 0) { // if i is even, use parenthesis to wrap i & 1
                // the last bit is 0, countBits(num) is the same as countBits(num >> 1)
                bits[i] = bits[i >> 1];
            } else {
                // the last bit is 1, countBits(num) is countBits(num - 1) + 1
                bits[i] = bits[i - 1] + 1;
            }
        }

        return bits;
    }

}
