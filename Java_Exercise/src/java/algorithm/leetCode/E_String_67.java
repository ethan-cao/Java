package algorithm.leetCode;

/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

### Example
"11",  "1"      --> "100"
"1010","1011"   --> "10101"

### Condition

### Essential problem

### Corner case
"0" + "0"

int, long might not be enough to hold the value
*/


public class E_String_67 {
    public static void main(String... args) {
        String a = "11";
        String b = "1";

        a = "1010";
        b = "1011";

//        a = "0";
//        b = "0";
//
        a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
        b = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011";

        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int l1 = a.length() - 1;
        int l2 = b.length() - 1;
        int carry = 0;

        while (l1 >= 0 || l2 >= 0) {
            int sum = carry;

            if (l1 >= 0) {
                sum += a.charAt(l1--) - '0';
            }

            if (l2 >= 0) {
                sum += b.charAt(l2--) - '0';
            }

            sb.append(sum % 2);
            carry = sum / 2;
        }

        if (carry != 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

    // bitwise operation
    public static String addBinary1(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int l1 = a.length() - 1;
        int l2 = b.length() - 1;
        int carry = 0;

        while (l1 >= 0 || l2 >= 0) {
            int sum = carry;

            if (l1 >= 0) {
                sum += a.charAt(l1--) - '0';
            }

            if (l2 >= 0) {
                sum += b.charAt(l2--) - '0';
            }

            sb.append(sum & 1);  // similar effect as sum % 2
            carry = sum >> 1; // sum >> 1 has similar effect as sum / 2
        }

        if (carry != 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }


    // This does not work since the long/int cannot hold large value
    public static String addBinary0(String a, String b) {
        String result = "";

        long sum = parse(a) + parse(b);
        long remainder = 0;
        long quotient = 2;
        long numerator = sum;

        while (quotient > 1) {
            quotient = numerator / 2;
            remainder = Math.floorMod(numerator, 2);
            result = remainder + result;

            numerator = quotient;
        }

        if (quotient != 0) {
            result = quotient + result;
        }

        return result;
    }

    private static long parse(String s) {
        long result = 0;

        for (int i = s.length() - 1; i >= 0; --i) {
            result += Integer.valueOf(s.charAt(i) + "") * Math.pow(2, s.length() - 1 - i);
        }

        System.out.println("s : " + result);

        return result;
    }
}
