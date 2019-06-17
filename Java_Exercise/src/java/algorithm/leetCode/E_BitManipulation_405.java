package algorithm.leetCode;

/*
Given an integer, write an algorithm to convert it to hexadecimal.
For negative integer, two’s complement method is used.

Note:
    1. All letters in hexadecimal (a-f) must be in lowercase.
    2. The hexadecimal string must not contain extra leading 0s.
        If the number is zero, it is represented by a single zero character '0';
        otherwise, the first character in the hexadecimal string will not be the zero character.
    3. The given number is guaranteed to fit within the range of a 32-bit signed integer.
    4. You must not use any method provided by the library which converts/formats the number to hex directly.

### Example
26 -> "1a"
-1 -> "ffffffff"

### Condition

### Essential problem
Decimal to hexadecimal

### Corner case

*/

public class E_BitManipulation_405 {

    public static void main(String... args) {
        System.out.println(toHex0(26)); // 1a
        System.out.println(toHex0(30)); // 1e
        System.out.println(toHex0(50)); // 32
        System.out.println(toHex0(500)); // 1f4
        System.out.println(toHex0(-1)); // ffffffff
        System.out.println(toHex0(-2)); // fffffffe
        // -1 = 0x ffffffff = 0b 11111111 11111111 11111111 11111111,  32 bit signed integer in two’s complement
    }

    public static String toHex0(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        /**
         * the signed int is conversed to a unsigned long, so negative number is presented in two’s complement
         * high-order 32 bits of the long are 0 and low-order 32 bits are equal to the bits of the integer
         *
         * 0xffffffffL: hexadecimal long, largest unsigned 2^32 bit int : 11111111 11111111 11111111 11111111
         *
         * zero and positive int values are mapped to a numerically equal long value
         * negative values are mapped to a long value equal to the input plus 2^32, (since 1st 2 is 2^32)
         * which is in two’s complement
         *
         * check java.lang.Integer.toUnsignedLong(int)
         */
        long n = num & 0xffffffffL;   // num & 0b11111111111111111111111111111111

        while (n > 16) {
            int remainder = (int) (n % 16);
            sb.append(map[remainder]);
            n = n / 16;
        }

        sb.append(map[(int) n]);

        return sb.reverse().toString();
    }

    // each time, check the last four digits of binary version of the input
    // and maps that to a hex char
    // then shift the input to the right by 4 bits, do it again until input becomes 0
    public static String toHex(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        while (num != 0) {
            sb.append(map[num & 0b1111]);
            // 0b1111 : binary 1111, which is 0xf : hexadecimal f, largest hex digit
            // num & 0b1111 : get the right-most hexadecimal digit
            // chars[num & 0b1111] : get corresponding hexadecimal char for the right-most hexadecimal digit

            num = num >>> 4;
            // logical shift right >>> is used to right-shifted 4 bit positions with zero-extension.
            // since negative number is represented as two’s complement, use >>> to shift the sign digit
            // so we can get correct negative hex number as two’s complement
            // check 补码 和 原码 及 互相转换 in Evernote
            // why 4 : 4 binary digits can represent the largest single hex digit
        }

        return sb.reverse().toString();
    }
}