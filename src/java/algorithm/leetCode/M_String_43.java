package algorithm.leetCode;

/*
Given two non-negative integers num1 and num2 represented as strings,
return the product of num1 and num2, also represented as a string.

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.

### Example
num1 = "2", num2 = "3" -> "6"
num1 = "123", num2 = "456" -> "56088"

*/

public class M_String_43 {

    public static void main(String... args) {
        System.out.println(multiply("2", "3"));      // 6
        System.out.println(multiply("7", "8"));      // 56
        System.out.println(multiply("123", "456"));  // 56088
    }

    // Time: O(N^M), Space: O(N+M)
    static String multiply(String num1, String num2) {
        if (num1.isEmpty() || num2.isEmpty()) {
            return "";
        }

        // used to host possible largest product digits
        int[] digits = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; --i) {
            for (int j = num2.length() - 1; j >= 0; --j) {
                int operand1 = num1.charAt(i) - '0';
                int operand2 = num2.charAt(j) - '0';
                int product = operand1 * operand2;

                int onesPlaceIdx = i + j + 1;
                int tensPlaceIdx = i + j;

                int sum = product + digits[onesPlaceIdx];

                digits[onesPlaceIdx] = sum % 10;
                digits[tensPlaceIdx] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int digit : digits) {
            // skip the 1st 0
            if (digit == 0 && sb.length() == 0) {
                continue;
            }
            sb.append(digit);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

}
