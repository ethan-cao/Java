package leetCode;

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

public class E_Math_BitManipulation_405 {

    public static void main(String... args) {
        System.out.println(toHex(26)); // 1a
        System.out.println(toHex(30)); // 1e
        System.out.println(toHex(50)); // 32
        System.out.println(toHex(-1)); // ffffffff
        // -1 = 0x ffffffff = 0b 11111111 11111111 11111111 11111111,  32 bit signed integer in two’s complement
    }

    public static String toHex0(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();


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

        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        while (num != 0) {
            sb.append(chars[num & 0b1111]);
            // 0b1111 : binary 1111, which is 0xf : hexadecimal f, largest hex digit
            // num & 0b1111 : get the right-most hexadecimal digit
            // chars[num & 0b1111] : get corresponding hexadecimal char for the right-most hexadecimal digit

            num = num >>> 4;
            // logical shift right >>> is used to right-shifted 4 bit positions with zero-extension.
            // since negative number is represented as two’s complement, use >>> to shift the sign digit
            // so we can get correct negative hex number as two’s complement
            // why 4 : 4 binary digits can represent the largest single hex digit
        }

        return sb.reverse().toString();
    }
}
