package algorithm.leetCode;

/*
Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ),
the plus + or minus sign -, non-negative integers and empty spaces .

### Example
"1 + 1" -> 2
"(1+(4+5+2)-3)+(6+8)" -> 23

*/

import java.util.ArrayDeque;
import java.util.Deque;

public class H_Stack_String_224 {

    public static void main(String... args) {
        System.out.println(calculate("(1+1)"));                    // 2
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));       // 23
    }

    // Stack
    // Time: O(N), Space: O(N)
    static int calculate(String s) {
        int result = 0;
        int operand = 0;
        int sign = 1;

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                operand = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    operand = operand * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += sign * operand;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                // put accumulate sum in the stack, together with the sign before (
                stack.push(result);
                stack.push(sign);

                // reset reuslt and sign, and proceed just like from beginning
                result = 0;
                sign = 1;
            } else if (c == ')') {
                // once finish accumualting sum in (),
                // take the result with sign and add with previous accumulated sum
                result = stack.pop() * result + stack.pop();
            }
        }

        return result;
    }

}