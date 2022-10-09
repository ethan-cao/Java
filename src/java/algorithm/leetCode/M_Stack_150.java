package algorithm.leetCode;

/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.

### Example
Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation:
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

*/

import java.util.*;

public class M_Stack_150 {

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }

    // Stack, 5ms
    // Time: O(N)
    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {

            if (token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-")) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = 0;

                switch (token) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        result = operand1 / operand2;
                        break;
                }

                stack.push(result);

            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.isEmpty() ? 0 : stack.peek();
    }

}
