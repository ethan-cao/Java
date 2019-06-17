package algorithm.leetCode;

/*
Reverse bits of a given 32 bits unsigned integer.

Note that in some languages such as Java, there is no unsigned integer type.
In this case, both input and output will be given as signed integer type and should not affect your implementation,

as the internal binary representation of the integer is the same whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation.
Therefore, in Example 2 above the input represents the signed integer -3 and the output represents the signed integer -1073741825.

### Example
Input:  00000010100101000001111010011100
Output: 00111001011110000010100101000000
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
so return 964176192 which its binary representation is 00111001011110000010100101000000.

Input: 11111111111111111111111111111101
Output: 10111111111111111111111111111111
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293,
so return 3221225471 which its binary representation is 10101111110010110010011101101001.

### Condition

### Essential problem

### Corner case

*/

public class E_BitManipulation_190 {

    public static void main(String... args) {
        System.out.println(reverseBits(0b00000010100101000001111010011100));
        System.out.println(reverseBits(0b11111111111111111111111111111101));
    }

    public static int reverseBits(int n) {
        int result = 0;

        // 32: because int is 32 bit
        for (int i = 0; i < 32; ++i) {
            int bit = n & 0b1;
            result += bit;
            n = n >>> 1;  // n >>>= 1;

            // except the last digit, other digits after getting revered, need to be left shifted.
            // so the next round can add the digit in right most position
            if (i < 31) {
                result = result << 1;
            }
        }

        return result;
    }

    /*
    for 8 bit binary number abcdefgh, the process is as follow:
    abcdefgh -> efghabcd -> ghefcdab -> hgfedcba

     O(log sizeof(int)
     */
    public static int reverseBits1(int n) {
        n = (n >> 16) | (n << 16);
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
        return n;
    }
}