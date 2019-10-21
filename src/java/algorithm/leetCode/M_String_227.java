package algorithm.leetCode;

/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

### Example
"3+2*2" -> 7
"3/2" -> 1
"3+5/2 " -> 5

*/

import java.util.LinkedList;
import java.util.List;

public class M_String_227 {

    public static void main(String... args) {
        System.out.println(calculate2("11*11"));    // 121
        System.out.println(calculate2("1+2"));      // 3
        System.out.println(calculate2("3/2"));      // 1
        System.out.println(calculate2("3+2*2"));    // 7
        System.out.println(calculate2("3+5/2"));    // 5
        System.out.println(calculate2("42343*14+0-3-2+5323/24+2+2-1*123"));    // 592899
    }

    // Too slow
    static int calculate(String s) {
        final String PLUS = "+";
        final String MINUS = "-";
        final String MULTIPLY = "*";
        final String DIVIDE = "/";

        List<String> inputs = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            String string = c + "";

            if (PLUS.equals(string) || MINUS.equals(string) || MULTIPLY.equals(string) || DIVIDE.equals(string)) {
                inputs.add(sb.toString());
                sb = new StringBuilder();

                inputs.add(string);
                continue;
            }

            sb.append(c);
        }
        inputs.add(sb.toString());

        for (int i = 0; i < inputs.size(); ) {
            String input = inputs.get(i);

            if (input.equals(MULTIPLY) || input.equals(DIVIDE)) {
                int operand1 = Integer.parseInt(inputs.get(i - 1));
                int operand2 = Integer.parseInt(inputs.get(i + 1));
                int result = input.equals(MULTIPLY) ? operand1 * operand2 : operand1 / operand2;

                // !!! while removing, index is changing!!!
                inputs.remove(i - 1);
                inputs.remove(i - 1);
                inputs.remove(i - 1);

                inputs.add(i - 1, result + "");
            } else {
                i++;
            }
        }

        for (int i = 0; i < inputs.size(); ) {
            String input = inputs.get(i);

            if (input.equals(PLUS) || input.equals(MINUS)) {
                int operand1 = Integer.parseInt(inputs.get(i - 1));
                int operand2 = Integer.parseInt(inputs.get(i + 1));
                int result = input.equals(PLUS) ? operand1 + operand2 : operand1 - operand2;

                inputs.remove(i - 1);
                inputs.remove(i - 1);
                inputs.remove(i - 1);

                inputs.add(i - 1, result + "");
            } else {
                i++;
            }
        }

        return Integer.parseInt(inputs.get(0));
    }

    // Stack
    // Time: O(), Space: O()
    static int calculate1(String s) {
        return 1;
    }

    // Time: O(N), Space: O(1)
    static int calculate2(String s) {
        final char PLUS = '+';
        final char MINUS = '-';
        final char MULTIPLY = '*';
        final char DIVIDE = '/';

        int operand1 = 0;
        char operator = '+';
        int operand2 = 0;
        int result = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {

                operand2 = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    operand2 = operand2 * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }

                if (operator == MULTIPLY) {
                    operand1 = operand1 * operand2;
                } else if (operator == DIVIDE) {
                    operand1 = operand1 / operand2;
                } else if (operator == PLUS) {
                    result += operand1;
                    operand1 = operand2;
                } else if (operator == MINUS) {
                    result += operand1;
                    operand1 = -operand2;
                }
            } else {
                operator = c;
            }
        }

        result += operand1;

        return result;
    }
}
